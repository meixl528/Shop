package com.ssm.websocket.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.ssm.common.dto.User;
import com.ssm.common.service.UserService;
import com.ssm.websocket.service.impl.SystemWebSocketHandler;

@Controller
public class WebSocketContrller {

	@Autowired
    private UserService userService;
	
	@Bean
    public SystemWebSocketHandler systemWebSocketHandler() {
        return new SystemWebSocketHandler();
    }


    @RequestMapping("/testWebSocket")
    @ResponseBody
    public String auditing(HttpServletRequest request){
        //无关代码都省略了
    	List<User> list = userService.getUserList();

        systemWebSocketHandler().sendMessageToUser((String)request.getSession().getAttribute("USER_NAME"), new TextMessage(list.size() + " 发送来了"));
        
        return "ok";
    }
	
}
