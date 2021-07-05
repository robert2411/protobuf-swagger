package com.github.robert2411.protobuf.swagger.mapper.customizers;

import com.github.robert2411.protobuf.swagger.mapper.MappingCustomizer;
import org.json.JSONObject;

import java.util.function.Function;

public class JsonConverter implements MappingCustomizer {
    private final Function<JSONObject, JSONObject> mapping;

    public JsonConverter(Function<JSONObject, JSONObject> mapping) {
        this.mapping = mapping;
    }

    @Override
    public String apply(String json) {
        return mapping.apply(new JSONObject(json)).toString();
    }
}
