package com.robert2411.protobuf.swagger.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;

public class ProtobufObjectMapper {
    private final ObjectMapper objectMapper;
    private final JsonFormat.Parser parser;
    private final JsonFormat.Printer printer;

    public ProtobufObjectMapper() {
        this(new ObjectMapper().disable(FAIL_ON_EMPTY_BEANS));
    }

    public ProtobufObjectMapper(ObjectMapper objectMapper) {
        this(objectMapper, JsonFormat.parser().ignoringUnknownFields());
    }

    public ProtobufObjectMapper(ObjectMapper objectMapper, JsonFormat.Parser parser) {
        this(objectMapper, parser, JsonFormat.printer());
    }

    public ProtobufObjectMapper(ObjectMapper objectMapper, JsonFormat.Parser parser, JsonFormat.Printer printer) {
        this.objectMapper = objectMapper;
        this.parser = parser;
        this.printer = printer;
    }

    public <T> T map(Object swagger, Class<T> clazz) throws IOException {
        return map(swagger, clazz, Collections.emptyList());
    }

    public <T> T map(Object swagger, Class<T> clazz, List<MappingCustomizer> mappingCustomizers) throws IOException {
        String json = objectToJson(swagger);
        json = applyCustomizers(json, mappingCustomizers);
        return jsonToObject(json, clazz);
    }

    public <T extends Message> T map(Object swagger, Supplier<T.Builder> protobufBuilder) throws JsonProcessingException, InvalidProtocolBufferException {
        return map(swagger, protobufBuilder, Collections.emptyList());
    }

    public <T extends Message> T map(Object swagger, Supplier<T.Builder> protobufBuilder, List<MappingCustomizer> mappingCustomizers) throws JsonProcessingException, InvalidProtocolBufferException {
        String json = objectToJson(swagger);
        json = applyCustomizers(json, mappingCustomizers);
        return jsonToProto(json, protobufBuilder);
    }

    public <P extends Message, T extends Message> T map(P protobuf, Supplier<T.Builder> protobufBuilder) throws IOException {
        return map(protobuf, protobufBuilder, Collections.emptyList());
    }

    public <P extends Message, T extends Message> T map(P protobuf, Supplier<T.Builder> protobufBuilder, List<MappingCustomizer> mappingCustomizers) throws IOException {
        String json = protoToJson(protobuf);
        json = applyCustomizers(json, mappingCustomizers);
        return jsonToProto(json, protobufBuilder);
    }

    public <P extends Message, T> T map(P protobuf, Class<T> clazz) throws IOException {
        return map(protobuf, clazz, Collections.emptyList());
    }

    public <P extends Message, T> T map(P protobuf, Class<T> clazz, List<MappingCustomizer> mappingCustomizers) throws IOException {
        String json = protoToJson(protobuf);
        json = applyCustomizers(json, mappingCustomizers);
        return jsonToObject(json, clazz);
    }

    public String applyCustomizers(String json, List<MappingCustomizer> mappingCustomizers) {
        String tempJson = json;
        for (MappingCustomizer customizer : mappingCustomizers) {
            tempJson = customizer.apply(tempJson);
        }
        return tempJson;
    }


    @SuppressWarnings("unchecked")
    public <T extends Message> T jsonToProto(String json, Supplier<T.Builder> protobufBuilder) throws InvalidProtocolBufferException {
        T.Builder builder = protobufBuilder.get();
        parser.merge(json, builder);
        return (T) builder.build();
    }

    public String objectToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public <P extends Message> String protoToJson(P protobuf) throws InvalidProtocolBufferException {
        return printer.print(protobuf);
    }

    public <T> T jsonToObject(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }
}