package com.github.robert2411.protobuf.swagger.mapper.customizers;

import org.junit.Assert;
import org.junit.Test;

public class RegexCustomizerTest {

    @Test
    public void apply() {
        String input = "This is a test string, the word is should be multiple times in this string, as is will be replaced";
        String regex = "(?<=[.>, ])(is)(?=[.<, ])";
        String replacement = "<h1>$1</h1>";
        String expected = "This <h1>is</h1> a test string, the word <h1>is</h1> should be multiple times in this string, as <h1>is</h1> will be replaced";

        RegexCustomizer customizer = new RegexCustomizer(regex, replacement);
        String result = customizer.apply(input);

        Assert.assertEquals(expected, result);
    }
}