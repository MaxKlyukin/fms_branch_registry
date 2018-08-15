package me.maxklyukin.fms_branch_registry.http;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Value;

public interface Response {

    public static Response empty() {
        return new EmptyResponse();
    }

    public static Response error(String error) {
        return new ErrorResponse(error);
    }

    public static Response data(Object data) {
        return new DataResponse(data);
    }

    @JsonSerialize
    class EmptyResponse implements Response {
    }

    @Value
    class ErrorResponse implements Response {
        private String error;
    }

    @Value
    class DataResponse implements Response {
        private Object data;
    }
}
