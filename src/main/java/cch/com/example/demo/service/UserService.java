package cch.com.example.demo.service;

import org.springframework.stereotype.Service;

import cch.com.example.demo.request.VO.UserRequestVO;
import cch.com.example.demo.response.VO.UserResponseVO;

@Service
public interface UserService {
    public UserResponseVO add(UserRequestVO user);

    public UserResponseVO update(UserRequestVO user);
    
    public UserResponseVO getUser(String id);
    
    public Boolean delete(String id);

}
