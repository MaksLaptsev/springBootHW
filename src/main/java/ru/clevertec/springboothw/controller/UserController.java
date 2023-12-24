package ru.clevertec.springboothw.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.springboothw.dto.channel.ChannelResponseOnlyNames;
import ru.clevertec.springboothw.dto.user.UserRequest;
import ru.clevertec.springboothw.dto.user.UserResponse;
import ru.clevertec.springboothw.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<UserResponse> update(@RequestBody @Valid UserRequest request, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(request, id));
    }

    @GetMapping({"/{id}"})
    public UserResponse get(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping({"/{id}/subscriptions"})
    public ResponseEntity<List<ChannelResponseOnlyNames>> getPersonSubscriptions(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getPersonSubscriptions(id));
    }
}
