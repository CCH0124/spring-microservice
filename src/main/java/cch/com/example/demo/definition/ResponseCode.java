package cch.com.example.demo.definition;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ResponseCode {
    SUCCESS(200, "success"),
    RESOURCES_NOT_EXIST (404, "Resource does not exist"),
    SERVICE_ERROR (500, "Server Exception"),
    NAME_IS_EXIST (422, "Name is exist");

    @Getter
    private Integer code;
    @Getter
    private String msg;

}
