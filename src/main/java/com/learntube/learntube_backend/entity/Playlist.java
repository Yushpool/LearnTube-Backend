package com.learntube.learntube_backend.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "playlists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String youtubeUrl;

    private int totalLessons;

    private int completedLessons;

    private double progress;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Lesson> lessons;

//    private boolean certificateUnlocked;
}