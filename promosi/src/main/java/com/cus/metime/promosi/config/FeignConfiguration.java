package com.cus.metime.promosi.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.cus.metime.promosi")
public class FeignConfiguration {

}
