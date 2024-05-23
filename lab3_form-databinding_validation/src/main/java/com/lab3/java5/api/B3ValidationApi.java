package com.lab3.java5.api;

import com.lab3.java5.dto.SachDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/java5-b3")
public class B3ValidationApi {

    @PostMapping("/validation-server-side")
    public ResponseEntity<?> doGetValidationServerSide(@Valid @RequestBody SachDTO sach) {
        Map<String, Object> resultApi = new HashMap<>();
        resultApi.put("status", true);
        resultApi.put("message", "Call Api Success");
        resultApi.put("data", sach);
        return ResponseEntity.ok(resultApi);
    }
}
