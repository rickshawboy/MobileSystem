/**   
 * @Copyright:  江西金磊科技发展有限公司  All rights reserved.Notice 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */  
package com.jeecms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.socket.ProductWebSocket;

import java.util.Map;
import java.util.concurrent.TimeUnit;
 
@Controller
public class IndexController {
 
 
    @GetMapping(value = "/")
    @ResponseBody
    public Object index() {
 
        return "Hello,ALl。This is yuanmayouchuang webSocket demo！";
    }
 
    @ResponseBody
    @GetMapping("test")
    public String test(String userId, String message) throws Exception {
        if (userId == "" || userId == null) {
            return "发送用户id不能为空";
        }
        if (message == "" || message == null) {
            return "发送信息不能为空";
        }
        new ProductWebSocket().systemSendToUser(userId, message);
        return "发送成功！";
    }
 
    @RequestMapping(value = "/ws")
    public String ws() {
        return "ws";
    }
 
    @RequestMapping(value = "/ws1")
    public String ws1() {
        return "ws1";
    }
}