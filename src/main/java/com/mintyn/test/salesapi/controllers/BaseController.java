package com.mintyn.test.salesapi.controllers;

import com.mintyn.test.salesapi.dtos.StandardResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
public abstract class BaseController {

    public ResponseEntity<StandardResponse> updateResponseStatus(StandardResponse response) {
        try {
            return switch (response.getStatus()) {
                case SUCCESS, PROCESSING -> new ResponseEntity<>(response, HttpStatus.OK);
                case CREATED -> new ResponseEntity<>(response, HttpStatus.CREATED);
                case NOT_FOUND -> new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                case NO_CONTENT -> new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
                case INTERNAL_ERROR -> new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                default -> new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            };
        }catch (Exception e) {
            log.error("An unknown exception occurred : {}", BaseController.class, e);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
