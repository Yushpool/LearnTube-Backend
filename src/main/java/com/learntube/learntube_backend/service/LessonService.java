package com.learntube.learntube_backend.service;

import com.learntube.learntube_backend.entity.Lesson;
import com.learntube.learntube_backend.entity.Playlist;
import com.learntube.learntube_backend.repository.LessonRepository;
import com.learntube.learntube_backend.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final PlaylistRepository playlistRepository;

    public LessonService(
            LessonRepository lessonRepository,
            PlaylistRepository playlistRepository) {

        this.lessonRepository = lessonRepository;
        this.playlistRepository = playlistRepository;
    }

    public Lesson createLesson(Lesson lesson){
        return lessonRepository.save(lesson);
    }

    public List<Lesson> getAllLessons(){
        return lessonRepository.findAll();
    }

    public Lesson getLessonById(Long id){
        return lessonRepository.findById(id)
                .orElseThrow();
    }

    public Lesson markComplete(Long id) {

        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        // Prevent duplicate completion
        if (!lesson.isCompleted()) {
            lesson.setCompleted(true);
            lessonRepository.save(lesson);
        }

        Playlist playlist = lesson.getPlaylist();

        if (playlist != null) {

            int completed = 0;

            for (Lesson l : playlist.getLessons()) {
                if (l.isCompleted()) {
                    completed++;
                }
            }

            playlist.setCompletedLessons(completed);

            double progress =
                    ((double) completed / playlist.getTotalLessons()) * 100;

            playlist.setProgress(progress);

            playlistRepository.save(playlist);
        }

        return lesson;
    }

    public Lesson updateWatchProgress(Long lessonId, int watchedSeconds) {

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow();

        lesson.setWatchedSeconds(watchedSeconds);

        return lessonRepository.save(lesson);
    }

    public Lesson updateProgress(
            Long lessonId,
            Integer currentTime,
            Integer duration
    ) {

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        lesson.setWatchedSeconds(currentTime);
        lesson.setDurationSeconds(duration);

        double percentage =
                duration == 0
                        ? 0
                        : (currentTime * 100.0) / duration;

        lesson.setWatchedPercentage(percentage);

        // Auto-complete lesson at 90%
        if (percentage >= 90) {

            lesson.setCompleted(true);

        }

        lessonRepository.save(lesson);

        Playlist playlist = lesson.getPlaylist();

        if (playlist != null) {

            int completed = 0;

            for (Lesson l : playlist.getLessons()) {

                if (l.isCompleted()) {

                    completed++;

                }

            }

            playlist.setCompletedLessons(completed);

            playlist.setProgress(
                    (completed * 100.0) / playlist.getTotalLessons()
            );

            playlistRepository.save(playlist);

        }

        return lesson;

    }



    public Lesson assignLessonToPlaylist(
            Long lessonId,
            Long playlistId){

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow();

        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow();

        lesson.setPlaylist(playlist);

        return lessonRepository.save(lesson);
    }
}