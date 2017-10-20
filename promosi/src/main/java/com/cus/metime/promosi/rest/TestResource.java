package com.cus.metime.promosi.rest;

import com.cus.metime.promosi.service.AssyncMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by C-US on 9/25/2017.
 */
@RestController
@RequestMapping("/public")
public class TestResource {

    private final AssyncMessagingService assyncMessagingService;

    @Autowired
    public TestResource (AssyncMessagingService assyncMessagingService){
        this.assyncMessagingService = assyncMessagingService;
    }

    @GetMapping("/index")
    public void sendIndexMessage(){
        //assyncMessagingService.sendIndexMessage(MessageEvent.UPDATE, promo);
    }

    @GetMapping("/file")
    public void senFileMessage() {
        //assyncMessagingService.sendFileMessage();
    }

}
