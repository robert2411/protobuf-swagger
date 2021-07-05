package com.github.robert2411.protobuf.swagger.mapper.customizers;

import org.junit.Assert;
import org.junit.Test;

public class JsonConverterTest {

    @Test
    public void apply() {
        String json = "{\"hello\":\"world\"}";
        JsonConverter jsonConverter = new JsonConverter(i -> i);
        Assert.assertEquals(json, jsonConverter.apply(json));
    }
}