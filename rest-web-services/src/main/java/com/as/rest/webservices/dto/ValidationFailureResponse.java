package com.as.rest.webservices.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationFailureResponse {

    private LocalDateTime timestamp;
    private String message;
    private int errorCount;
    private Map<String, String> validationErrors;

    public void formatValidationResults(BindingResult bindingResult) {
        if(bindingResult!= null && bindingResult.hasFieldErrors()) {
            this.validationErrors = new HashMap<>();
            this.errorCount = bindingResult.getErrorCount();
            bindingResult.getFieldErrors().forEach(error -> this.validationErrors.put(error.getField(),error.getDefaultMessage()));
        }
    }

}
