package cch.com.example.demo.response.VO;

import java.io.Serializable;

import cch.com.example.demo.definition.ResponseCode;
import lombok.Data;

@Data
public class ResponseResult<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public ResponseResult(Integer code, String msg , T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }   
    
    public ResponseResult() {
        
    }

    public static ResponseResult<Void> success() {
        return new ResponseResult<Void>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg() , null);
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), data);
    }
}
