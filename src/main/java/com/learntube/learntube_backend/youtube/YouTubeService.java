package com.learntube.learntube_backend.youtube;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class YouTubeService {

    @Value("${youtube.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    // Returns all videos inside playlist
    public YouTubeResponse getPlaylistVideos(String playlistId) {

        String url =
                "https://www.googleapis.com/youtube/v3/playlistItems"
                        + "?part=snippet"
                        + "&maxResults=50"
                        + "&playlistId=" + playlistId
                        + "&key=" + apiKey;

        return restTemplate.getForObject(url, YouTubeResponse.class);
    }

    // Returns playlist information (title)
    public PlaylistInfoResponse getPlaylistInfo(String playlistId) {

        String url =
                "https://www.googleapis.com/youtube/v3/playlists"
                        + "?part=snippet"
                        + "&id=" + playlistId
                        + "&key=" + apiKey;

        return restTemplate.getForObject(url, PlaylistInfoResponse.class);
    }
}