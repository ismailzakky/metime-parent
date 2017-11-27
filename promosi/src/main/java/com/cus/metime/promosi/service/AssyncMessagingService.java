package com.cus.metime.promosi.service;

import com.cus.metime.promosi.config.messaging.Promosi;
import com.cus.metime.promosi.domain.Promo;
import com.cus.metime.shared.messaging.EventWrapperDTO;
import com.cus.metime.shared.messaging.MessageEvent;
import com.cus.metime.shared.messaging.filehandler.FileTransferDTO;
import com.cus.metime.shared.messaging.promotion.PromoMessagingDTO;
import com.cus.metime.shared.messaging.promotion.builder.PromoMessagingDTOBuilder;
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
    private final PromoService promoService;

    @Autowired
    public AssyncMessagingService(Promosi outputChannelSource,PromoService promoService){
        this.outputChannelSource = outputChannelSource;
        this.promoService = promoService;
    }

    public void sendIndexMessage(MessageEvent event, Promo promo){

        MessageChannel messageChannel = outputChannelSource.indexOutput();

        EventWrapperDTO eventWrapperDTO = new EventWrapperDTO();
        eventWrapperDTO.setMessage(new PromoMessagingDTOBuilder()
                .setStartDate(promo.getValidityPeriod().getStartDate())
                .setEndDate(promo.getValidityPeriod().getEndDate())
                .setPromoCategory(promo.getPromoCategory().toString())
                .setSalondUUID(promo.getSegment())
                .setPromoUUID(promo.getUuid())
                .setImagePublicId(promo.getCloudinaryImage().getUrl())
                .setImageSecurlUrl(promo.getCloudinaryImage().getSecureUrl())
                .setImagePublicId(promo.getCloudinaryImage().getPublicId())
                .createPromoMessagingDTO());
        eventWrapperDTO.setEvent(event);

        Message<EventWrapperDTO> eventWrapperDTOMessage = MessageBuilder.withPayload(eventWrapperDTO).build();
        messageChannel.send(eventWrapperDTOMessage);
    }

    /*public void sendImageFile(MultipartFile multipartFile,String fileName,Promo promo,MessageEvent event) throws IOException {
        MessageChannel messageChannel = outputChannelSource.fileOutput();
        EventWrapperDTO eventWrapperDTO = new EventWrapperDTO();

        FileTransferDTO fileTransferDTO = new FileTransferDTO(fileName,multipartFile.getBytes(),multipartFile.getOriginalFilename().split("\\.")[1],promo.getUuid());
        eventWrapperDTO.setEvent(event);
        eventWrapperDTO.setMessage(fileTransferDTO);


        Message<EventWrapperDTO> msg = MessageBuilder.withPayload(eventWrapperDTO).build();
        messageChannel.send(msg);
    }
*/
}
