package cch.com.example.demo.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cch.com.example.demo.definition.ResponseCode;
import cch.com.example.demo.exception.BaseException;
import cch.com.example.demo.response.VO.ResponseResult;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseResult<Void> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseResult<Void>(ResponseCode.SERVICE_ERROR.getCode(), ResponseCode.SERVICE_ERROR.getMsg(), null);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseResult<Void> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return new ResponseResult<Void>(ResponseCode.SERVICE_ERROR.getCode(), ResponseCode.SERVICE_ERROR.getMsg(), null);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseResult<Void> handleBaseException(BaseException e) {
        log.error(e.getMessage(), e);
        ResponseCode code = e.getCode();
        return new ResponseResult<Void>(code.getCode(), code.getMsg(), null);
    }

}
