package cch.com.example.demo.service;

import org.springframework.stereotype.Service;

import cch.com.example.demo.request.VO.UserRequestVO;
import cch.com.example.demo.response.VO.ResponseResult;
import cch.com.example.demo.response.VO.UserResponseVO;

@Service
public interface UserService {
    public ResponseResult<UserResponseVO> add(UserRequestVO user) throws Exception;

    public ResponseResult<UserResponseVO> update(UserRequestVO user);
    
    public ResponseResult<UserResponseVO> getUser(String id);
    
    public ResponseResult<Object> delete(String id);

}
