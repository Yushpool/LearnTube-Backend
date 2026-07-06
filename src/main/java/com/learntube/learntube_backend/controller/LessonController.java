package com.learntube.learntube_backend.controller;

import com.learntube.learntube_backend.dto.ProgressRequest;
import com.learntube.learntube_backend.entity.Lesson;
import com.learntube.learntube_backend.service.LessonService;
import org.springframework.web.bind.annotation.*;
import com.learntube.learntube_backend.dto.WatchProgressRequest;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@CrossOrigin("*")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public Lesson createLesson(@RequestBody Lesson lesson) {
        return lessonService.createLesson(lesson);
    }

    @GetMapping
    public List<Lesson> getAllLessons() {
        return lessonService.getAllLessons();
    }

    @GetMapping("/{id}")
    public Lesson getLessonById(@PathVariable Long id) {
        return lessonService.getLessonById(id);
    }

    @PostMapping("/{id}/complete")
    public Lesson markComplete(@PathVariable Long id) {
        return lessonService.markComplete(id);
    }

    @PutMapping("/{id}/watch-progress")
    public Lesson updateWatchProgress(
            @PathVariable Long id,
            @RequestBody WatchProgressRequest request) {

        return lessonService.updateWatchProgress(
                id,
                request.getWatchedSeconds()
        );
    }

    @PutMapping("/{lessonId}/playlist/{playlistId}")
    public Lesson assignLessonToPlaylist(
            @PathVariable Long lessonId,
            @PathVariable Long playlistId) {

        return lessonService.assignLessonToPlaylist(
                lessonId,
                playlistId
        );
    }

    @PutMapping("/{id}/progress")
    public Lesson updateProgress(
            @PathVariable Long id,
            @RequestBody ProgressRequest request
    ) {

        System.out.println("Current Time = " + request.getCurrentTime());
        System.out.println("Duration = " + request.getDuration());

        return lessonService.updateProgress(
                id,
                request.getCurrentTime(),
                request.getDuration()
        );

    }
}