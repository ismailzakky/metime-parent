package com.cus.metime.review.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.cus.metime.review")
public class FeignConfiguration {

}
