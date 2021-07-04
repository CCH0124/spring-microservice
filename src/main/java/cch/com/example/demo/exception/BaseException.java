package cch.com.example.demo.exception;

import cch.com.example.demo.definition.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseException extends RuntimeException {
    private ResponseCode code;

    public BaseException(ResponseCode code) {
        this.code = code;
    }

    public BaseException(Throwable cause, ResponseCode code) {
        super(cause);
        this.code = code;
    }
}
