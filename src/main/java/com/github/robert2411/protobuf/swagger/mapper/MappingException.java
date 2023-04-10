package com.github.robert2411.protobuf.swagger.mapper;

public class MappingException extends RuntimeException {
     public MappingException(String message) {
        super(message);
    }

    public MappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MappingException(Throwable cause) {
        super(cause);
    }

}