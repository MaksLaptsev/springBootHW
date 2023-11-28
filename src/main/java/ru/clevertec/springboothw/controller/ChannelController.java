package ru.clevertec.springboothw.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.clevertec.springboothw.dto.channel.ChannelRequest;
import ru.clevertec.springboothw.dto.channel.ChannelResponse;
import ru.clevertec.springboothw.dto.channel.ChannelResponseFull;
import ru.clevertec.springboothw.service.ChannelService;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/channel")
public class ChannelController {
    private final ChannelService channelService;

    @Autowired
    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @PostMapping
    public ResponseEntity<ChannelResponseFull> save(@RequestParam @NotNull Long authorId,
                                                    @RequestPart @Valid ChannelRequest request,
                                                    @RequestPart MultipartFile file){

        return ResponseEntity.status(HttpStatus.CREATED).body(channelService.save(request,authorId,file));
    }

    @PutMapping
    public ResponseEntity<ChannelResponseFull> update(@RequestParam @NotNull Long channelId,
                                                      @RequestPart @Valid ChannelRequest request,
                                                      @RequestPart MultipartFile file){

        return ResponseEntity.status(HttpStatus.CREATED).body(channelService.update(request,channelId,file));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<ChannelResponseFull> get(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(channelService.findById(id));
    }

    @GetMapping({"/filter"})
    public ResponseEntity<List<ChannelResponse>> getWithFilter(@RequestParam(defaultValue = "") String name,
                                                               @RequestParam(defaultValue = "") String language,
                                                               @RequestParam(defaultValue = "") String category,
                                                               @PageableDefault(sort = {"id"}) Pageable pageable){
        List<ChannelResponse> channelResponses = new ArrayList<>();
        if (!name.isEmpty()){
            channelResponses = channelService.findAllByNameContainingIgnoreCase(name,pageable);
        } else if (!language.isEmpty()) {
            channelResponses = channelService.findAllByLanguageContainingIgnoreCase(language,pageable);
        } else if (!category.isEmpty()) {
            channelResponses = channelService.findAllByCategoryContainingIgnoreCase(category, pageable);
        }else {
            throw new RuntimeException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(channelResponses);
    }

    @PutMapping({"/subscribe"})
    public ResponseEntity<ChannelResponse> personSubscribeToChannel(@RequestParam @NotNull Long channelId,
                                                                    @RequestParam @NotNull Long personId){
        return ResponseEntity.status(HttpStatus.OK).body(channelService.subscribeToChannel(channelId,personId));
    }

    @PutMapping({"/unsubscribe"})
    public ResponseEntity<ChannelResponse> personUnSubscribeFromChannel(@RequestParam @NotNull Long channelId,
                                                                        @RequestParam @NotNull Long personId){
        return ResponseEntity.status(HttpStatus.OK).body(channelService.unSubscribeFromChannel(channelId,personId));
    }
}
