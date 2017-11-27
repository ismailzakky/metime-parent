package com.cus.metime.uaa.service;



import com.cus.metime.shared.messaging.EventWrapperDTO;
import com.cus.metime.shared.messaging.MessageEvent;
import com.cus.metime.shared.messaging.filehandler.FileTransferDTO;
import com.cus.metime.uaa.config.messaging.UaaProcessor;
import com.cus.metime.uaa.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Created by C-US on 10/3/2017.
 */
@Service
public class AssyncMessagingService {




}
