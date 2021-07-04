package cch.com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cch.com.example.demo.mapper.UserMapper;
import cch.com.example.demo.request.VO.UserRequestVO;
import cch.com.example.demo.response.VO.UserResponseVO;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserResponseVO add(UserRequestVO user) throws Exception {
        // TODO Auto-generated method stub
        if (userMapper.isExistUser(user.getName())) {
            throw new Exception("Name is exist");
        }
        return userMapper.add(user);
    }

    @Override
    public UserResponseVO update(UserRequestVO user) {
        // TODO Auto-generated method stub
        return userMapper.update(user);
    }

    @Override
    public UserResponseVO getUser(String id) {
        // TODO Auto-generated method stub
        return userMapper.getById(id);
    }

    @Override
    public Boolean delete(String id) {
        // TODO Auto-generated method stub
        if (userMapper.delete(id) != 0) {
            return true;
        }
        return false;
    }
    
}
