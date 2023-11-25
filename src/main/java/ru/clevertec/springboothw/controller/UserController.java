package ru.clevertec.springboothw.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.springboothw.dto.user.UserRequest;
import ru.clevertec.springboothw.dto.user.UserResponse;
import ru.clevertec.springboothw.dto.user.UserRequestRecord;
import ru.clevertec.springboothw.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest request){
        System.out.println(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<UserResponse> update(@RequestBody UserRequest request, @PathVariable Long id){
        System.out.println(id);
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(request,id));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<UserResponse> get(@RequestBody UserRequest request, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getById(id));
    }
}
