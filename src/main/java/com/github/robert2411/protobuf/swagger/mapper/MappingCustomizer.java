package com.github.robert2411.protobuf.swagger.mapper;

/**
 * This interface should be implemented if you want to generate modifiers for the json converters.
 */
@FunctionalInterface
public interface MappingCustomizer {

    /**
     * Customize a mapping on the json and return the resulting json as a string
     *
     * @param json the json in string format
     * @return The json with the applied mapping
     */
    String apply(String json);
}