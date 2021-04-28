package util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author admin
 */
public class JedisPoolUtil {

    /**
     *  准备一个redis  连接池对象
     */
    private static JedisPool pool = null;

    static {
        //读取配置文件
        InputStream is = JedisPoolUtil.class.getClassLoader().getResourceAsStream("jedis.properties");
        //创建Properties集合对象
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取文件数据   设置到JedisPoolConfig
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(properties.getProperty("redis.pool.maxTotal")));
        config.setMaxIdle(Integer.parseInt(properties.getProperty("redis.pool.maxIdle")));
        config.setMinIdle(Integer.parseInt(properties.getProperty("redis.pool.minIdle")));
        //初始化JedisPool
        pool = new JedisPool(config,properties.getProperty("redis.ip"), Integer.parseInt(properties.getProperty("redis.port")));
    }

    /**
     * 对外提供一个获取Jedis的连接
     */
    public static Jedis getJedis(){
        return pool.getResource();
    }
}
