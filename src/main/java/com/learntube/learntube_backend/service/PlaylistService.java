package com.learntube.learntube_backend.service;

import com.learntube.learntube_backend.entity.Playlist;
import com.learntube.learntube_backend.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public Playlist createPlaylist(Playlist playlist){
        return playlistRepository.save(playlist);
    }

    public List<Playlist> getAllPlaylists(){
        return playlistRepository.findAll();
    }

    public Playlist getPlaylistById(Long id){
        return playlistRepository.findById(id)
                .orElseThrow();
    }
}