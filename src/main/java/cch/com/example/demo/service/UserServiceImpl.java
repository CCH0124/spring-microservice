package cch.com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cch.com.example.demo.definition.ResponseCode;
import cch.com.example.demo.mapper.UserMapper;
import cch.com.example.demo.request.VO.UserRequestVO;
import cch.com.example.demo.response.VO.ResponseResult;
import cch.com.example.demo.exception.BaseException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public ResponseResult add(UserRequestVO user) throws Exception {
        // TODO Auto-generated method stub
        if (userMapper.isExistUser(user.getName())) {
            throw new BaseException(ResponseCode.NAME_IS_EXIST);
        }
        return new ResponseResult().success(userMapper.add(user));
    }

    @Override
    public ResponseResult update(UserRequestVO user) {
        // TODO Auto-generated method stub
        return new ResponseResult().success(userMapper.update(user));
    }

    @Override
    public ResponseResult getUser(String id) {
        // TODO Auto-generated method stub
        return new ResponseResult().success(userMapper.getById(id));
    }

    @Override
    public ResponseResult delete(String id) {
        // TODO Auto-generated method stub
        Map<String, Boolean> map = new HashMap<>();
        if (userMapper.delete(id) != 0) {
            map.put("status", true);
            return new ResponseResult().success(map);
        }
        map.put("status", false);
        return new ResponseResult().success(map);
    }
    
}
