package com.practice.learningnavigator.Controller;

import com.practice.learningnavigator.Dto.ErrorDetails;
import com.practice.learningnavigator.Exceptions.NotAIntegerException;
import com.practice.learningnavigator.Service.ApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hidden-feature")
public class NumbersAPIController {

    @Autowired
    private ApiServiceImpl apiService;

    private final String url = "http://numberapi.com";

    @GetMapping("/{number}")
    public ResponseEntity<?> getFactsAboutNumber(@PathVariable String number){
        try{
            String response = apiService.callExternalApi(url,number);
            return ResponseEntity.ok(response);
        }catch (NotAIntegerException e){
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), e.getMessage(), "Enter a valid Integer");
            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        }

    }

}
