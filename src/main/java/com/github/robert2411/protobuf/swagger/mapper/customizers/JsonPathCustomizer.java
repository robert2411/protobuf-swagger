package com.github.robert2411.protobuf.swagger.mapper.customizers;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.github.robert2411.protobuf.swagger.mapper.MappingCustomizer;

import java.util.function.Function;

//https://github.com/json-path/JsonPath
public class JsonPathCustomizer<S, T> implements MappingCustomizer {
    private final Configuration configuration = Configuration.defaultConfiguration();
    private final String sourceJsonPath;
    private final String targetJsonPath;
    private final String targetKey;
    private final Function<S, T> mapping;

    public JsonPathCustomizer(String sourceJsonPath, String targetJsonPath, String targetKey, Function<S, T> mapping) {
        this.sourceJsonPath = sourceJsonPath;
        this.targetJsonPath = targetJsonPath;
        this.targetKey = targetKey;
        this.mapping = mapping;
    }

    @Override
    public String apply(String json) {
        S source = readFieldFromJson(json, sourceJsonPath);
        String tempJson = deleteValueInJson(json, sourceJsonPath);
        return putValueInJson(tempJson, targetJsonPath, targetKey, mapping.apply(source));
    }

    public S readFieldFromJson(String json, String jsonPath) {
        return JsonPath
                .using(configuration)
                .parse(json)
                .read(jsonPath);
    }

    public String putValueInJson(String json, String jsonPath, String key, T value) {
        return JsonPath
                .using(configuration)
                .parse(json)
                .put(JsonPath.compile(jsonPath), key, value)
                .jsonString();
    }

    public String deleteValueInJson(String json, String jsonPath) {
        return JsonPath
                .using(configuration)
                .parse(json)
                .delete(jsonPath)
                .jsonString();
    }
}