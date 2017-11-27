package com.cus.metime.uaa.config.messaging;

import com.cus.metime.shared.messaging.EventWrapperDTO;
import com.cus.metime.shared.messaging.MessageEvent;
import com.cus.metime.shared.messaging.salon.SalonHandlerDTO;
import com.cus.metime.uaa.domain.User;
import com.cus.metime.uaa.domain.builder.UserBuilder;
import com.cus.metime.uaa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

/**
 * Created by C-US on 10/3/2017.
 */
@Configuration
@MessageEndpoint
public class MessageProcessor {

    private final UserService userService;

    @Autowired
    public MessageProcessor(UserService userService) {
        this.userService = userService;
    }

    @ServiceActivator(inputChannel = "salonInput")
    public void salonInputProcessor(Message<EventWrapperDTO> r){

        EventWrapperDTO eventWrapperDTO = r.getPayload();
        if(eventWrapperDTO != null){

            if(eventWrapperDTO.getEvent() == MessageEvent.CREATE){
                processNewSalonManager((SalonHandlerDTO) eventWrapperDTO.getMessage());
            } else if(eventWrapperDTO.getEvent() == MessageEvent.CREATE.UPDATE) {

            }  else if(eventWrapperDTO.getEvent() == MessageEvent.DELETE){

            }

        }


    }

    private void processNewSalonManager(SalonHandlerDTO salonHandlerDTO){

        if(salonHandlerDTO != null){
            User user = new UserBuilder().createUser();

            userService.saveNewSalonManager(user, salonHandlerDTO.getSalonId());

        }



    }

}
