package com.as.rest.webservices.temp;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue getFilter(){
        FilterSome filterSome = FilterSome.builder().field1("value1").field2("value2").field3("value3").build();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(filterSome);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("FilterSome1", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue getFilterList(){
        List<FilterSome> filterSomeList = Arrays.asList(FilterSome.builder().field1("value1").field2("value2").field3("value3").build(),
                FilterSome.builder().field1("value4").field2("value5").field3("value6").build());
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(filterSomeList);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
        SimpleFilterProvider filters = new SimpleFilterProvider().addFilter("FilterSome1", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}
