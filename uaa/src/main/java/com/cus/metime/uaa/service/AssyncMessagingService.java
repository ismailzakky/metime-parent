package com.cus.metime.uaa.service;



import com.cus.metime.shared.messaging.EventWrapperDTO;
import com.cus.metime.shared.messaging.MessageEvent;
import com.cus.metime.uaa.config.messaging.UaaProcessor;
import com.cus.metime.uaa.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Created by C-US on 10/3/2017.
 */
//@Service
public class AssyncMessagingService {

    @Autowired
    private UaaProcessor outputChannelSource;

    public void sendImageFile(MultipartFile multipartFile, String fileName, User user, MessageEvent event) throws IOException {
        MessageChannel messageChannel = outputChannelSource.fileOutput();
        EventWrapperDTO eventWrapperDTO = new EventWrapperDTO();
        ObjectMapper objectMapper = new ObjectMapper();

        //FileTransferDTO fileTransferDTO = new FileTransferDTO(fileName, multipartFile.getBytes(), multipartFile.getOriginalFilename().split("\\.")[1], user);
        eventWrapperDTO.setEvent(event);
        //eventWrapperDTO.setMessage(fileTransferDTO);

        Map<String, Object> eventWrapperMap = objectMapper.convertValue(eventWrapperDTO, Map.class);

        Message<Map<String, Object>> msg = MessageBuilder.withPayload(eventWrapperMap).build();
        messageChannel.send(msg);
    }


}
