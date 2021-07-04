package cch.com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cch.com.example.demo.request.VO.UserRequestVO;
import cch.com.example.demo.response.VO.ResponseResult;
import cch.com.example.demo.service.UserService;

@RestController
public class Usercontroller {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseResult addUser(@Valid @RequestBody UserRequestVO user) throws Exception {
        return userService.add(user);
    }
    
    @DeleteMapping("{id}")
    public ResponseResult deleteUser(@PathVariable(value = "id") String id) {
        return userService.delete(id);
    }

    @PutMapping("/update/{id}")
    public ResponseResult updateUser(@Valid @RequestBody UserRequestVO user) {
        return userService.update(user);
    }

    @GetMapping("{id}")
    public ResponseResult updateUser(@PathVariable(value = "id") String id) {
        return userService.getUser(id);
    }
    
}
