package cch.com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cch.com.example.demo.request.VO.UserRequestVO;
import cch.com.example.demo.service.UserService;

@RestController
public class Usercontroller {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserRequestVO user) throws Exception {
        return new ResponseEntity<>(userService.add(user), HttpStatus.OK);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") String id) {
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserRequestVO user) {
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> updateUser(@PathVariable(value = "id") String id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }
    
}
