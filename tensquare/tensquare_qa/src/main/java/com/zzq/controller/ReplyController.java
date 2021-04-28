package com.zzq.controller;

import com.zzq.model.Reply;
import com.zzq.service.ReplyService;
import model.PageResult;
import model.Result;
import model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author Zhang zq
 * @Date 2021/4/26 16:20
 * @Description
 */
@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    /**
     * 增加回答
     * @param reply  回答信息
     * @return   返回状态信息
     */
    @PostMapping()
    public Result add(@RequestBody Reply reply){
        replyService.add(reply);
        return new Result(true, StatusCode.OK,"successful operation");
    }

    /**
     * 回答全部列表
     * @return  返回结果集
     */
    @GetMapping()
    public Result queryAll(){
        List<Reply> list = replyService.queryAll();
        return new Result(true,StatusCode.OK,"成功查询到数据",list);
    }

    /**
     * 根据id查询回答信息
     * @param replyId   回答id
     * @return      返回结果
     */
    @GetMapping("/{replyId}")
    public Result queryById(@PathVariable String replyId){
        Reply reply = replyService.queryById(replyId);
        return new Result(true,StatusCode.OK,"操作成功",reply);
    }

    /**
     * 修改reply
     * @param reply   回答详情
     * @param replyId   回答id
     * @return       返回状态信息
     */
    @PutMapping("/{replyId}")
    public Result update(@RequestBody Reply reply,@PathVariable String replyId){
        reply.setId(replyId);
        replyService.update(reply);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 根据id删除
     * @param replyId  回答信息id
     * @return     返回状态信息
     */
    @DeleteMapping("/{replyId}")
    public Result delete(@PathVariable String replyId){
        replyService.delete(replyId);
        return new Result(true,StatusCode.OK,"操作成功");
    }

    /**
     * 回答分页
     * @param searchMap  搜索条件
     * @param page       当前页
     * @param size       页面大小
     * @return        返回结果集
     */
    @PostMapping("/search/{page}/{size}")
    public Result search(@RequestBody Map searchMap, @PathVariable int page,@PathVariable int size){
        Page<Reply> replyPage = replyService.searchPageAndLike(searchMap,page,size);
        return new Result(true,StatusCode.OK,"查询成功",
                new PageResult<Reply>(replyPage.getTotalElements(),replyPage.getContent()));
    }


    /**
     * 根据问题id查询回答列表
     * @param problemId  问题id
     * @return   返回结果集
     */
    @GetMapping("/problem/{problemId}")
    public Result queryByProblemId(@PathVariable String problemId){
        List<Reply> list = replyService.queryByProblemId(problemId);
        return new Result(true,StatusCode.OK,"成功查询到数据",list);
    }

    /**
     * 回答问题
     * @param reply  回答信息详情
     * @return     返回状态信息
     */
    @PostMapping("/save")
    public Result saveReply(@RequestBody Reply reply){
        replyService.saveReply(reply);
        return  new Result(true , StatusCode.OK,"操作成功");
    }

    /**
     * 我的回答列表
     * @param page  当前页
     * @param size   页面大小
     * @return      返回结果信息
     */
    @GetMapping("/list/{page}/{size}")
    public Result myList(@PathVariable int page ,@PathVariable int size){
        Page<Reply> list  = replyService.myList(page,size);
        return new Result(true,StatusCode.OK,"查询成功",
                new PageResult<Reply>(list.getTotalElements(),list.getContent()));
    }
}
