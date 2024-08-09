package com.wealth.telegram.controller;

import com.wealth.telegram.model.base.APIResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1.0/test")
public class TestController {

    @GetMapping(value = "/hi")
    public APIResponse<?> hi() {
        return APIResponse.success(Map.of("key", "value"));
    }

    @PostMapping(value = "/hi")
    public APIResponse<?> postHi(@RequestBody Map<String, Object> request) {
        return APIResponse.success(Map.of("key", "value"));
    }

}
