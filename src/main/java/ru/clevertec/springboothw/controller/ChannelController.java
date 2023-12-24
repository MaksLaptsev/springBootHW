package ru.clevertec.springboothw.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.clevertec.springboothw.dto.channel.ChannelRequest;
import ru.clevertec.springboothw.dto.channel.ChannelResponse;
import ru.clevertec.springboothw.dto.channel.ChannelResponseFull;
import ru.clevertec.springboothw.service.ChannelService;
import ru.clevertec.springboothw.validation.multipartfile.ValidMediaType;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/channels")
public class ChannelController {
    private final ChannelService channelService;

    @PostMapping
    public ResponseEntity<ChannelResponseFull> save(@RequestPart @Valid ChannelRequest request,
                                                    @RequestPart(required = false) @ValidMediaType MultipartFile file) {

        return ResponseEntity.status(HttpStatus.CREATED).body(channelService.save(request, file));
    }

    @PutMapping("/{channelId}")
    public ResponseEntity<ChannelResponseFull> update(@PathVariable @NotNull Long channelId,
                                                      @RequestPart @Valid ChannelRequest request,
                                                      @RequestPart(required = false) @ValidMediaType MultipartFile file) {

        return ResponseEntity.status(HttpStatus.CREATED).body(channelService.update(request, channelId, file));
    }

    @GetMapping({"/{id}"})
    public ChannelResponseFull get(@PathVariable Long id) {
        return channelService.findById(id);
    }

    @GetMapping()
    public ResponseEntity<List<ChannelResponse>> getWithFilter(@RequestParam(defaultValue = "") String name,
                                                               @RequestParam(defaultValue = "") String language,
                                                               @RequestParam(defaultValue = "") String category,
                                                               @PageableDefault(sort = {"id"}) Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(channelService.findAllByFilters(name, language, category, pageable));
    }

    @PostMapping({"/{channelId}/subscriptions/{personId}"})
    public ResponseEntity<ChannelResponse> personSubscribeToChannel(@PathVariable @NotNull Long channelId,
                                                                    @PathVariable @NotNull Long personId) {
        return ResponseEntity.status(HttpStatus.OK).body(channelService.subscribeToChannel(channelId, personId));
    }

    @DeleteMapping({"/{channelId}/subscriptions/{personId}"})
    public ResponseEntity<ChannelResponse> personUnSubscribeFromChannel(@PathVariable @NotNull Long channelId,
                                                                        @PathVariable @NotNull Long personId) {
        return ResponseEntity.status(HttpStatus.OK).body(channelService.unSubscribeFromChannel(channelId, personId));
    }
}
