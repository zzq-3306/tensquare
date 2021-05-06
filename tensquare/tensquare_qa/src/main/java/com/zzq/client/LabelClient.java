package com.zzq.client;

import model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Zhang zq
 * @Date 2021/5/4 14:46
 * @Description     @FeignClient注解用于指定从哪个服务中调用功能 ，
 *                      注意 里面的名称与被调用的服务名保持一致，并且不能包含下划线。
 *
 *                  @RequestMapping注解用于对被调用的微服务进行地址映射。
 *                  注意@PathVariable注解一定要指定参数名称，否则出错
 */
@FeignClient("tensquare-base")
public interface LabelClient {

    @GetMapping(value = "/label/{id}")
    Result findById(@PathVariable("id") String id);
}
