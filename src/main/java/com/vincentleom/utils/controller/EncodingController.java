package com.vincentleom.utils.controller;

import com.vincentleom.utils.response.EncodingResponse;
import com.vincentleom.utils.service.EncodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/encoding")
public class EncodingController {

    private final EncodingService encodingService;

    @Autowired
    public EncodingController(EncodingService encodingService) {
        this.encodingService = encodingService;
    }

    /**
     * if password is not null, return with its encoded
     * if password is null generate a password with given length (default 32) and return with its encoded
     *
     * @param text String - password to be encoded
     * @param length int - password length to be used for generating if given password is null
     * @return EncodingResponse - cleartext, bcrypted and base64 encoded password
     */
    @GetMapping("")
    public ResponseEntity<EncodingResponse> encode(
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "length", required = false, defaultValue = "0") int length) {

        if(text == null) {
            if (length == 0) length = 32;
            text = this.encodingService.generatePassayPassword(length);
        }

        return ResponseEntity.ok(new EncodingResponse(
                text,
                this.encodingService.encode(text),
                Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8))
        ));
    }
}
