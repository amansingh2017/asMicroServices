package com.as.rest.webservices.temp;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonFilter("FilterSome1")
public class FilterSome {

    private String field1;
    private String field2;
    private String field3;

}
