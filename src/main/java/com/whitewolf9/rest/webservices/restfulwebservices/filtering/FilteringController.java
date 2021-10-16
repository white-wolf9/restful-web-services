package com.whitewolf9.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
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
    public SomeBean returnSomeBean(){
        return new SomeBean("value1", "value2", "value3");
    }

    @GetMapping("/filtering-list")
    public List<SomeBean> returnSomeBeanList(){
        return Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4", "value5", "value6"));
    }

    @GetMapping("/filtering-dynamically")
    public MappingJacksonValue returnSomeBeanFilteringDynamically(){
        SomeBeanDynamic someBeanDynamic = new SomeBeanDynamic("value1", "value2", "value3");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("fieldOne", "fieldTwo");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanDynamicFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(someBeanDynamic);
        mapping.setFilters(filters);

        return mapping;

    }

    @GetMapping("/filtering-dynamically-list")
    public MappingJacksonValue returnSomeBeanListFilteringDynamically(){
        List<SomeBeanDynamic> someBeansList = Arrays.asList(new SomeBeanDynamic("value1", "value2", "value3"),
                new SomeBeanDynamic("value4", "value5", "value6"));
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("fieldTwo", "fieldThree");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanDynamicFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(someBeansList);
        mapping.setFilters(filters);

        return mapping;
    }

}
