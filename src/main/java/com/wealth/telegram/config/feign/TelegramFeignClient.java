package com.wealth.telegram.config.feign;

import com.wealth.telegram.config.FeignBaseConfiguration;
import com.wealth.telegram.model.dto.telegram.GetUpdateResponse;
import com.wealth.telegram.model.dto.telegram.SendMessageRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "telegram", url = "${telegram.base-url:https://api.telegram.org}", configuration = FeignBaseConfiguration.class)
public interface TelegramFeignClient {

    @GetMapping("/bot{token}/getUpdates")
    GetUpdateResponse getUpdate(@PathVariable String token);

    @PostMapping("/bot{token}/sendMessage")
    void sendMessage(@PathVariable("token") String token, @RequestParam("chat_id") long chatId, @RequestParam("text") String text);

    @PostMapping(value = "/bot{token}/sendMessage", consumes = "application/json")
    void sendMessage(@PathVariable("token") String token, @RequestBody SendMessageRequest request);

}
