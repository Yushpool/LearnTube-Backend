package com.learntube.learntube_backend.controller;

import com.learntube.learntube_backend.dto.ImportRequest;
import com.learntube.learntube_backend.entity.Playlist;
import com.learntube.learntube_backend.service.ImportService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/import")
@CrossOrigin("*")
public class ImportController {

    private final ImportService importService;

    public ImportController(ImportService importService){

        this.importService = importService;

    }

    @PostMapping

    public Playlist importPlaylist(@RequestBody ImportRequest request){

        System.out.println("IMPORT API HIT");


        return importService.importPlaylist(request);

    }

}