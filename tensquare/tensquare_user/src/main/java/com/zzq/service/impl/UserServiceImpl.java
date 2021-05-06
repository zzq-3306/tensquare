package com.zzq.service.impl;


import com.zzq.mapper.FollowMapper;
import com.zzq.model.Follow;
import com.zzq.mapper.UserMapper;
import com.zzq.model.Login;
import com.zzq.model.User;
import com.zzq.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author Zhang zq
 * @Date 2021/4/23 14:52
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FollowMapper followMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 密码加密
     */
    @Autowired
    private BCryptPasswordEncoder encoder;


    /**
     * 发送邮箱验证   在redis中设置过期时间   并发送到rabbitmq中
     * @param email   邮箱
     */
    @Override
    public String sendEmail(String email){
        //生成6位短信验证码
        Random random = new Random();
        int max = 999999;
        int min = 100000;
        int code = random.nextInt(max);
        if (code<min){
            code=code+min;
        }
        System.out.println(email+"收到的验证码是: "+code);

        //将验证码放入redis  保证key唯一的情况下  设置5分钟过期
        redisTemplate.opsForValue().set("code_"+email,code+"",5, TimeUnit.MINUTES);

        //将手机号和验证码发送到rabbitmq中
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("email",email);
        hashMap.put("code",code+"");
        rabbitTemplate.convertAndSend("code",hashMap);
        return code+"";
    }







    /**
     * 添加用户信息
     * @param user  用户信息
     * @param code  用户输入的验证码
     */
    @Override
    public void add(User user,String code){
        String sysCode = (String) redisTemplate.opsForValue().get("code_" + user.getEmail());
        System.out.println("redis中的code: "+sysCode);
        System.out.println("用户输入的的code: "+code);

        if (code != null && code.equals(sysCode)){
            //加密后的新密码
            String newPassword = encoder.encode(user.getPassword());
            System.out.println("newPassword = " + newPassword);
            user.setPassword(newPassword);
            userMapper.save(user);
        }
    }

    /**
     * 查询全部用户信息
     * @return   返回结果集
     */
    @Override
    public List<User> queryAll(){
        return userMapper.findAll();
    }

    /**
     * 用户登陆查询
     * @param login
     * @return
     */
    @Override
    public User queryByLogin(Login login) {

        User redisUser = (User) redisTemplate.opsForValue().get("loginUser");
        if (redisUser != null){

            //判断密码是否相同
            if (encoder.matches(login.getPassword(),redisUser.getPassword())){
                System.out.println("使用redisTemplate的方法从redis中查到了user信息...");
                return redisUser;
            }
            return null;


        }else{
            User user = userMapper.queryByMobile(login.getMobile());
            if (user != null && encoder.matches(login.getPassword(),user.getPassword())){
                System.out.println("使用redisTemplate的方法从mysql中查到了user信息...");
                return user;
            }
        }
        System.out.println("使用redisTemplate的方法从redis和mysql中 都未 查到了user信息...");
        return null;


//        User user = null;
//        ObjectMapper objectMapper = new ObjectMapper();
//        //获得一个redis连接
//        Jedis jedis = JedisPoolUtil.getJedis();
//        String loginUser = jedis.get("loginUser");
//        if (loginUser!=null){
//            //说明redis中有登陆人的信息
//            try {
//
//                //字符串转换对象
//                return objectMapper.readValue(loginUser,User.class);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }else {
//            //说明redis中没有登陆人的信息  需要查询数据库
//            user = userMapper.queryByLogin(login);
//            System.out.println("mysql.user = " + user);
//            if (user != null){
//                //将mysql查询到的信息放到redis中
//                try {
//                    String jsonUser = objectMapper.writeValueAsString(user);
//                    jedis.set("loginUser",jsonUser);
//                } catch (JsonProcessingException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("从数据库中查到了登陆人的信息...");
//                return user;
//            }
//        }
//        System.out.println("redis和mysql都为查询到信息...");
//        return user;
    }

    /**
     * 根据用户id查询用户信息
     * @param userId   用户id
     * @return     返回状态信息
     */
    @Override
    public User queryById(String userId) {
        return userMapper.findById(userId).get();
    }

    /**
     * 修改用户信息
     * @param user       用户的信息
     * @return         返回状态信息
     */
    @Override
    public User update(User user) {
        User u = userMapper.save(user);
        return u;
    }

    /**
     * 根据用户id删除信息
     * @param userId    用户id
     * @return      返回状态信息
     */
    @Override
    public void delete(String userId) {
        userMapper.deleteById(userId);
    }

    /**
     * 关注用户根据用户id
     * @param userId  用户id
     */
    @Override
    @Transactional
    public int updateById(String userId) {
        return userMapper.updateFollowById(userId);
    }

    /**
     * 修改tb_follow表
     * @param follow    关注的对象和被关注的对象 的id
     * @return      返回状态信息
     */
    @Override
    @Transactional
    public int addFollow(Follow follow) {
        System.out.println("follow = " + follow);
      // return userMapper.addFollow(follow);
        return followMapper.addFollow(follow);
    }
//    @Override
//    public int updateFollow(String userid,String targetuser) {
//        System.out.println("userid = " + userid + ", targetuser = " + targetuser);
//       return userMapper.addFollow(userid,targetuser);
//    }

    /**
     * 删除用户关注
     * @param userId   用户的id
     * @return      返回状态信息
     */
    @Override
    @Transactional
    public int deleteFollowById(String userId) {
        return userMapper.deleteFollowById(userId);
    }

    /**
     * 删除tb_follow中的关注信息
     */
     @Override
     @Transactional
    public int deleteFollow(Follow follow) {
        return followMapper.deleteFollow(follow);
    }

    /**
     * 显示用户带条件查询并分页
     * @param searchMap    搜索条件
     * @param page         当前页
     * @param size          页面大小
     * @return           返回状态信息
     */
    @Override
    public Page<User> queryLikeAndPage(Map searchMap, int page, int size) {
        Specification<User> spectification = createSpectification(searchMap);
        PageRequest of = PageRequest.of(page, size);
        return userMapper.findAll(spectification,of);
    }

    /**
     * 查询我的粉丝
     * @param id  我的Id
     * @return  返回结果list
     */
    @Override
    public List<User> queryFansById(String id) {
        System.out.println("id = " + id);
        return userMapper.queryFansById(id);
    }

    /**
     * 根据我的id   查询我的关注
     * @param id   我的id
     * @return     返回结果集
     */
    @Override
    public List<User> queryFollowById(String id) {
        return userMapper.queryFollowById(id);
    }

    /**
     * 查询我的关注列表ID
     * @param id   我的id
     * @return      返回列表id集合   List
     */
    @Override
    public List<String> queryMyFollowId(String id) {
        return followMapper.queryMyFollowId(id);
    }

    /**
     * 添加用户
     * @param user  用户信息
     */
    @Override
    public void insert(User user) {
        //加密后的新密码
        String newPassword = encoder.encode(user.getPassword());
        System.out.println("newPassword = " + newPassword);
        user.setPassword(newPassword);

        userMapper.save(user);
    }

    /**
     * 定义条件查询的私有方法
     * @param searchMap
     * @return
     */
    private Specification<User>  createSpectification(Map searchMap){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                ArrayList<Predicate> list = new ArrayList<>();

                if (searchMap.get("id")!=null &&!"".equals(searchMap.get("id"))){
                    list.add(criteriaBuilder.like(root.get("id").as(String.class), (String) searchMap.get("id")));
                }
                if (searchMap.get("mobile")!=null &&!"".equals(searchMap.get("mobile"))){
                    list.add(criteriaBuilder.like(root.get("mobile").as(String.class), (String) searchMap.get("mobile")));
                }
                if (searchMap.get("nickname")!=null &&!"".equals(searchMap.get("nickname"))){
                    list.add(criteriaBuilder.like(root.get("nickname").as(String.class), (String) searchMap.get("nickname")));
                }
                if (searchMap.get("sex")!=null &&!"".equals(searchMap.get("sex"))){
                    list.add(criteriaBuilder.equal(root.get("sex").as(String.class), (String) searchMap.get("sex")));
                }
                if (searchMap.get("email")!=null &&!"".equals(searchMap.get("email"))){
                    list.add(criteriaBuilder.like(root.get("email").as(String.class), (String) searchMap.get("email")));
                }
                if (searchMap.get("regdate")!=null &&!"".equals(searchMap.get("regdate"))){
                    list.add(criteriaBuilder.like(root.get("regdate").as(String.class), (String) searchMap.get("regdate")));
                }
                if (searchMap.get("updatedate")!=null &&!"".equals(searchMap.get("updatedate"))){
                    list.add(criteriaBuilder.like(root.get("updatedate").as(String.class), (String) searchMap.get("updatedate")));
                }
                if (searchMap.get("lastdate")!=null &&!"".equals(searchMap.get("lastdate"))){
                    list.add(criteriaBuilder.like(root.get("lastdate").as(String.class), (String) searchMap.get("lastdate")));
                }
                if (searchMap.get("online")!=null &&!"".equals(searchMap.get("online"))){
                    list.add(criteriaBuilder.like(root.get("online").as(String.class), (String) searchMap.get("online")));
                }
                if (searchMap.get("interest")!=null &&!"".equals(searchMap.get("interest"))){
                    list.add(criteriaBuilder.like(root.get("interest").as(String.class), (String) searchMap.get("interest")));
                }
                if (searchMap.get("personality")!=null &&!"".equals(searchMap.get("personality"))){
                    list.add(criteriaBuilder.like(root.get("personality").as(String.class), (String) searchMap.get("personality")));
                }
                if (searchMap.get("fanscount")!=null &&!"".equals(searchMap.get("fanscount"))){
                    list.add(criteriaBuilder.like(root.get("fanscount").as(String.class), (String) searchMap.get("fanscount")));
                }
                if (searchMap.get("followcount")!=null &&!"".equals(searchMap.get("followcount"))){
                    list.add(criteriaBuilder.like(root.get("followcount").as(String.class), (String) searchMap.get("followcount")));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
    }


}
