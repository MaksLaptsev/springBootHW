package ru.clevertec.springboothw.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.springboothw.dto.channel.ChannelResponseOnlyNames;
import ru.clevertec.springboothw.dto.user.UserRequest;
import ru.clevertec.springboothw.dto.user.UserResponse;
import ru.clevertec.springboothw.dto.user.UserRequestRecord;
import ru.clevertec.springboothw.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequest request){
        System.out.println(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<UserResponse> update(@RequestBody @Valid UserRequest request, @PathVariable Long id){
        System.out.println(id);
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(request,id));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<UserResponse> get(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getById(id));
    }

    @GetMapping({"/subscriptions"})
    public ResponseEntity<List<ChannelResponseOnlyNames>> getPersonSubscriptions(@RequestParam @NotNull Long personId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getPersonSubscriptions(personId));
    }
}
