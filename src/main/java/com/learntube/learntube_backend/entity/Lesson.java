package com.learntube.learntube_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String videoUrl;

    private boolean completed;


    private Integer watchedSeconds = 0;


    private Integer durationSeconds = 0;


    private Double watchedPercentage = 0.0;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    @JsonBackReference
    private Playlist playlist;
}