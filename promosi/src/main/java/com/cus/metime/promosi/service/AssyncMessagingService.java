package com.cus.metime.promosi.service;

import com.cus.metime.promosi.config.messaging.Promosi;
import com.cus.metime.promosi.domain.Promo;
import com.cus.metime.promosi.dto.EventWrapperDTO;
import com.cus.metime.promosi.dto.FileTransferDTO;
import com.cus.metime.promosi.dto.MessageEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by C-US on 9/25/2017.
 */
@Service
public class AssyncMessagingService {

    private final Promosi outputChannelSource;

    @Autowired
    public AssyncMessagingService(Promosi outputChannelSource){
        this.outputChannelSource = outputChannelSource;
    }

    public void sendIndexMessage(MessageEvent event, Promo promo){

        MessageChannel messageChannel = outputChannelSource.indexOutput();

        EventWrapperDTO eventWrapperDTO = new EventWrapperDTO();
        eventWrapperDTO.setMessage(promo);
        eventWrapperDTO.setEvent(event);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> promoMap = objectMapper.convertValue(promo,Map.class);
        Map<String,Object> eventMap = new HashMap();
        eventMap.put("event",event.toString());
        eventMap.put("message",promoMap);


        Message<Map<String,Object>> eventWrapperDTOMessage = MessageBuilder.withPayload(eventMap).build();



        messageChannel.send(eventWrapperDTOMessage);
    }

    public void sendImageFile(MultipartFile multipartFile,String fileName,Promo promo,MessageEvent event) throws IOException {
        MessageChannel messageChannel = outputChannelSource.fileOutput();
        EventWrapperDTO eventWrapperDTO = new EventWrapperDTO();
        ObjectMapper objectMapper = new ObjectMapper();

        FileTransferDTO fileTransferDTO = new FileTransferDTO(fileName,multipartFile.getBytes(),multipartFile.getOriginalFilename().split("\\.")[1],promo.getUuid());
        eventWrapperDTO.setEvent(event);
        eventWrapperDTO.setMessage(fileTransferDTO);

        Map<String,Object> eventWrapperMap = objectMapper.convertValue(eventWrapperDTO,Map.class);

        Message<Map<String,Object>> msg = MessageBuilder.withPayload(eventWrapperMap).build();
        messageChannel.send(msg);
    }

}
