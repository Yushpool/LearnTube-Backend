package com.learntube.learntube_backend.controller;

import com.learntube.learntube_backend.youtube.YouTubeResponse;
import com.learntube.learntube_backend.youtube.YouTubeService;
import com.learntube.learntube_backend.youtube.YouTubeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final YouTubeService youTubeService;

    public TestController(YouTubeService youTubeService) {
        this.youTubeService = youTubeService;
    }

    @GetMapping("/api/test")
    public YouTubeResponse test(@RequestParam String url) {

        String playlistId = YouTubeUtils.extractPlaylistId(url);

        return youTubeService.getPlaylistVideos(playlistId);
    }
}