package com.learntube.learntube_backend.service;

import com.learntube.learntube_backend.dto.ImportRequest;
import com.learntube.learntube_backend.entity.Lesson;
import com.learntube.learntube_backend.entity.Playlist;
import com.learntube.learntube_backend.repository.PlaylistRepository;
import com.learntube.learntube_backend.youtube.PlaylistInfoResponse;
import com.learntube.learntube_backend.youtube.YouTubeResponse;
import com.learntube.learntube_backend.youtube.YouTubeService;
import com.learntube.learntube_backend.youtube.YouTubeUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImportService {

    private final PlaylistRepository playlistRepository;
    private final YouTubeService youTubeService;

    public ImportService(
            PlaylistRepository playlistRepository,
            YouTubeService youTubeService) {

        this.playlistRepository = playlistRepository;
        this.youTubeService = youTubeService;
    }

    public Playlist importPlaylist(ImportRequest request) {

        String playlistId =
                YouTubeUtils.extractPlaylistId(request.getUrl());

        PlaylistInfoResponse info =
                youTubeService.getPlaylistInfo(playlistId);

        YouTubeResponse videos =
                youTubeService.getPlaylistVideos(playlistId);

        Playlist playlist = new Playlist();

        playlist.setTitle(
                info.getItems().get(0).getSnippet().getTitle()
        );

        playlist.setYoutubeUrl(request.getUrl());

        playlist.setCompletedLessons(0);

        playlist.setProgress(0);

        List<Lesson> lessons = new ArrayList<>();

        for (YouTubeResponse.Item item : videos.getItems()) {

            Lesson lesson = new Lesson();

            lesson.setTitle(
                    item.getSnippet().getTitle()
            );

            String videoId =
                    item.getSnippet()
                            .getResourceId()
                            .getVideoId();

            lesson.setVideoUrl(
                    "https://www.youtube.com/watch?v=" + videoId
            );

            lesson.setCompleted(false);

            lesson.setWatchedSeconds(0);

            lesson.setDurationSeconds(0);

            lesson.setWatchedPercentage(0.0);

            lesson.setPlaylist(playlist);

            lessons.add(lesson);
        }

        playlist.setLessons(lessons);

        playlist.setTotalLessons(lessons.size());

        return playlistRepository.save(playlist);
    }
}