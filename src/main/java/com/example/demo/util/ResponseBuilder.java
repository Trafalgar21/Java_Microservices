package com.example.demo.util;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
public class ResponseBuilder {
    Map<String, Object> response = new LinkedHashMap();

    @JsonAnySetter
    public void setResponse(String key, Object value){
        response.put(key, value);
    }
}
