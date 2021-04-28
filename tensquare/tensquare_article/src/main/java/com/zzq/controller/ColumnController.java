package com.zzq.controller;

import com.zzq.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Zhang zq
 * @Date 2021/4/28 21:06
 * @Description
 */
@RestController
@RequestMapping("column")
public class ColumnController {

    @Autowired
    private ColumnService columnService;


}
