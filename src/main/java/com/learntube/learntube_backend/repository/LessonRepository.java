package com.learntube.learntube_backend.repository;

import com.learntube.learntube_backend.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}