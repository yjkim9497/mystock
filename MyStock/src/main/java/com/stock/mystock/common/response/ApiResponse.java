package com.stock.mystock.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@JsonPropertyOrder({"code","message","data"})
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ApiResponse<T> {
    private final HttpStatus status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(HttpStatus.OK,null,data);
    }

    public static <T> ApiResponse<T> success(T data,String message) {
        return new ApiResponse<T>(HttpStatus.OK, message, data);
    }
    public static <T> ApiResponse<T> error(ErrorCode errorCode) {
        return new ApiResponse<T>(errorCode.getStatus(),errorCode.getMessage(),null);
    }
    public static <T> ApiResponse<T> error(HttpStatus status , T data,String message) {
        return new ApiResponse<T>(status,message,data);
    }

    public static <T> ApiResponse<T> error(HttpStatus status, String message) {
        return new ApiResponse<T>(status,message,null);
    }

    /**
     * Json 형태로 반환
     * @return
     */
    public String toJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

}
