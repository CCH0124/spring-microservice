package cch.com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cch.com.example.demo.request.VO.UserRequestVO;
import cch.com.example.demo.response.VO.UserResponseVO;
import cch.com.example.demo.service.UserService;

@RestController
public class Usercontroller {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserResponseVO> addUser(@Valid @RequestBody UserRequestVO user) {
        return ResponseEntity.ok().body(userService.add(user));
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(userService.delete(id));
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponseVO> updateUser(@Valid @RequestBody UserRequestVO user) {
        return ResponseEntity.ok().body(userService.update(user));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseVO> updateUser(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(userService.getUser(id));
    }
    
}
