package cch.com.example.demo.service;

import org.springframework.stereotype.Service;

import cch.com.example.demo.request.VO.UserRequestVO;
import cch.com.example.demo.response.VO.ResponseResult;
import cch.com.example.demo.response.VO.UserResponseVO;

@Service
public interface UserService {
    public ResponseResult add(UserRequestVO user) throws Exception;

    public ResponseResult update(UserRequestVO user);
    
    public ResponseResult getUser(String id);
    
    public ResponseResult delete(String id);

}
