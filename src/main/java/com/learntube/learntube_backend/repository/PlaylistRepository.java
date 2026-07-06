package com.learntube.learntube_backend.repository;

import com.learntube.learntube_backend.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}