package com.example.demo.controllers;

import com.example.demo.models.Greeting;
import com.example.demo.models.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.UUID;

@Controller
public class GreetingController {
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Greeting greeting(@Payload HelloMessage message) throws Exception {
//        System.out.println(message);
//        System.out.println("jdlaslkjdaskdklasdjlkasdjlasdjlkaskjldaskjldkjlasdkjlasdkjlasdsdasdasdasdads");
        UUID uuid = UUID.randomUUID();

//        Thread.sleep(1000); // simulated delay
        return new Greeting(uuid.toString());
    }

}