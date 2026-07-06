package com.learntube.learntube_backend.youtube;

import lombok.Data;
import java.util.List;

@Data
public class YouTubeResponse {

    private List<Item> items;

    @Data
    public static class Item {

        private Snippet snippet;

    }

    @Data
    public static class Snippet {

        private String title;

        private ResourceId resourceId;

    }

    @Data
    public static class ResourceId {

        private String videoId;

    }

}