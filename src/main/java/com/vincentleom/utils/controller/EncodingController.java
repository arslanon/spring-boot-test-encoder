package com.vincentleom.utils.controller;

import com.vincentleom.utils.response.EncodingResponse;
import com.vincentleom.utils.service.EncodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/encoding")
public class EncodingController {

    final private EncodingService encodingService;

    @Autowired
    public EncodingController(EncodingService encodingService) {
        this.encodingService = encodingService;
    }

    /**
     * if text is not null, return with encoded text
     * if text is null generate a text with given length (default 32) and return with encoded text
     *
     * @param text String - text to be encoded
     * @param length int - random text length
     * @return EncodingResponse - cleartext text and encoded text
     */
    @GetMapping("")
    public ResponseEntity<EncodingResponse> encode(
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "length", required = false, defaultValue = "0") int length) {

        if(text == null) {
            if (length == 0) length = 32;
            text = this.encodingService.generateText(length);
        }

        return ResponseEntity.ok(new EncodingResponse(
                text,
                this.encodingService.encode(text)
        ));
    }
}
