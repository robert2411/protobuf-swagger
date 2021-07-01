package com.robert2411.protobuf.swagger.mapper.customizers;

import com.robert2411.protobuf.swagger.mapper.customizers.JsonPathCustomizer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.json;

public class JsonPathCustomizerTest {
    private static final String JSON =
            "{\n" +
                    "  \"greeting\": \"hello\",\n" +
                    "  \"test\": [\"world\"]\n" +
                    "}";


    @Test
    public void writeValueToJson() throws IOException {
        JsonPathCustomizer<String, List<String>> mapper = new JsonPathCustomizer<String, List<String>>("$", "testkey", "", s -> Collections.singletonList(s));
        String json = mapper.putValueInJson(JSON, "$", "testkey", Collections.singletonList("bla"));

        assertThatJson(json)
                .isEqualTo(json("{\"greeting\":\"hello\",\"test\":[\"world\"],\"testkey\":[\"bla\"]}"));
    }

    @Test
    public void readAndPut() throws IOException {
        JsonPathCustomizer<List<String>, List<String>> mapper = new JsonPathCustomizer<List<String>, List<String>>("$.test", "$", "testkey", i -> i);
        String json = mapper.apply(JSON);

        assertThatJson(json)
                .isEqualTo(json("{\"greeting\": \"hello\", \"testkey\":[\"world\"]}"));
    }

    @Test
    public void readFieldFromJson() throws IOException {
        JsonPathCustomizer<List<String>, List<String>> mapper = new JsonPathCustomizer<List<String>, List<String>>("$.test", "$", "testkey", i -> i);

        List<String> object = mapper.readFieldFromJson(JSON, "$.test.[*]");

        Assert.assertEquals(1, object.size());
        Assert.assertEquals("world", object.get(0));
    }
}