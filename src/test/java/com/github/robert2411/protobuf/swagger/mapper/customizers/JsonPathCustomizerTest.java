package com.github.robert2411.protobuf.swagger.mapper.customizers;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.json;

public class JsonPathCustomizerTest {
    private static final String JSON =
            """
                    {
                      "greeting": "hello",
                      "test": ["world"]
                    }
                    """;


    @Test
    public void writeValueToJson() {
        JsonPathCustomizer<String, List<String>> mapper = new JsonPathCustomizer<String, List<String>>("$", "testkey", "", Collections::singletonList);
        String json = mapper.putValueInJson(JSON, "$", "testkey", Collections.singletonList("bla"));

        assertThatJson(json)
                .isEqualTo(json("{\"greeting\":\"hello\",\"test\":[\"world\"],\"testkey\":[\"bla\"]}"));
    }

    @Test
    public void readAndPut() {
        JsonPathCustomizer<List<String>, List<String>> mapper = new JsonPathCustomizer<List<String>, List<String>>("$.test", "$", "testkey", i -> i);
        String json = mapper.apply(JSON);

        assertThatJson(json)
                .isEqualTo(json("{\"greeting\": \"hello\", \"testkey\":[\"world\"]}"));
    }

    @Test
    public void readFieldFromJson() {
        JsonPathCustomizer<List<String>, List<String>> mapper = new JsonPathCustomizer<List<String>, List<String>>("$.test", "$", "testkey", i -> i);

        List<String> object = mapper.readFieldFromJson(JSON, "$.test.[*]");

        Assert.assertEquals(1, object.size());
        Assert.assertEquals("world", object.get(0));
    }
}