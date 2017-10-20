package com.cus.metime.promosi.config.messaging;

import com.cus.metime.promosi.dto2.EventWrapperDTO;
import com.cus.metime.promosi.dto2.FileTransferDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by C-US on 9/25/2017.
 */
@Configuration
@MessageEndpoint
public class PromosiProcessor {

    @ServiceActivator(inputChannel = "indexInput")
    public void indexInputProcessor(Message<byte[]> r) throws IOException, ClassNotFoundException {

        ObjectMapper objectMapper = new ObjectMapper();

        EventWrapperDTO eventWrapperDTO = objectMapper.convertValue(deserialize(r.getPayload()),EventWrapperDTO.class);

        Map<String,Object> map = objectMapper.convertValue(eventWrapperDTO.getMessage(),Map.class);

        System.out.println(map);
        System.out.println(r);
    }

    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try(ByteArrayInputStream b = new ByteArrayInputStream(bytes)){
            try(ObjectInputStream o = new ObjectInputStream(b)){
                return o.readObject();
            }
        }
    }

    public static void copyObject(Object src, Object dest)
        throws IllegalArgumentException, IllegalAccessException,
        NoSuchFieldException, SecurityException {
        for (Field field : src.getClass().getFields()) {
            dest.getClass().getField(field.getName()).set(dest, field.get(src));
        }
    }

    @ServiceActivator(inputChannel = "fileInput")
    public void fileInputProcessor(Message<byte[]> r) throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {

        ObjectMapper objectMapper = new ObjectMapper();

        EventWrapperDTO eventWrapperDTO = objectMapper.convertValue(deserialize(r.getPayload()),EventWrapperDTO.class);

        Map<String,Object> map = objectMapper.convertValue(eventWrapperDTO.getMessage(),Map.class);

        if(eventWrapperDTO.getEvent().toString() == "DELETE"){
            System.out.println("DELETE FILE AND DATA");
        } else {
            FileTransferDTO fileTransferDTO = new FileTransferDTO();
            fileTransferDTO.setFileExtension((String) map.get("fileExtension"));
            fileTransferDTO.setFileName((String) map.get("fileName"));
            fileTransferDTO.setFileStream((byte[]) map.get("fileStream"));
            Map<String,Object> promoMap = objectMapper.convertValue(map.get("promo"),Map.class);

            byte[] file = fileTransferDTO.getFileStream();
            String fileName =  fileTransferDTO.getFileName();
            String fileExtension = fileTransferDTO.getFileExtension();

            String name = "test";
            if(file.length > 0){
                byte[] bytes = file;
                BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File("D:/opt/" + fileName+"."+fileExtension)));
                stream.write(bytes);
                stream.close();
                System.out.println("You successfully uploaded " + name + " into " + name + "-uploaded !");
            }
        }
        System.out.println(r);
    }

}
