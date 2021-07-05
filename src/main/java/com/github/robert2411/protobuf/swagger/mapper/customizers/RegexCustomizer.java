package com.github.robert2411.protobuf.swagger.mapper.customizers;

import com.github.robert2411.protobuf.swagger.mapper.MappingCustomizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexCustomizer implements MappingCustomizer {
    private final Pattern pattern;
    private final String replace;

    public RegexCustomizer(String pattern, String replace, int patternSettings) {
        this.replace = replace;
        this.pattern = Pattern.compile(pattern, patternSettings);
    }

    public RegexCustomizer(String pattern, String replace) {
        this(pattern, replace, 0);
    }

    @Override
    public String apply(String json) {
        Matcher matcher = pattern.matcher(json);
        return matcher.replaceAll(replace);
    }
}