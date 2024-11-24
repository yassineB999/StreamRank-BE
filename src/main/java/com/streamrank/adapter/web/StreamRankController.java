package com.streamrank.adapter.web;

import com.streamrank.adapter.wrapper.ResponseWrapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class StreamRankController {
    @GetMapping
    public ResponseEntity<?> HelloWorld(){
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success("Hello World!"));
    }

    @GetMapping("/error")
    public ResponseEntity<?> Error(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseWrapper.error("Internal Error"));
    }

    @GetMapping("/ping")
    public ResponseEntity<?> Ping(){
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success("pong"));
    }
}
