package ru.clevertec.springboothw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.springboothw.dto.channel.ChannelRequest;
import ru.clevertec.springboothw.dto.channel.ChannelResponse;
import ru.clevertec.springboothw.dto.channel.ChannelResponseFull;
import ru.clevertec.springboothw.service.ChannelService;
import ru.clevertec.springboothw.service.impl.ChannelServiceImpl;

@RestController
@RequestMapping("/channel")
public class ChannelController {
    private ChannelService channelService;

    @Autowired
    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @PostMapping
    public ResponseEntity<ChannelResponseFull> save(@RequestBody ChannelRequest request){
        System.out.println(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(channelService.save(request));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<ChannelResponseFull> get(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(channelService.findById(id));
    }


}
