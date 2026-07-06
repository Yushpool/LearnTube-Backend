package com.learntube.learntube_backend.youtube;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouTubeUtils {

    // Playlist ID
    public static String extractPlaylistId(String url) {

        Pattern pattern = Pattern.compile("[?&]list=([a-zA-Z0-9_-]+)");

        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

    // Video ID
    public static String extractVideoId(String url) {

        Pattern pattern = Pattern.compile("(?:v=|youtu\\.be/)([a-zA-Z0-9_-]{11})");

        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

    // Is Playlist?
    public static boolean isPlaylist(String url) {
        return url.contains("list=");
    }

    // Is Single Video?
    public static boolean isVideo(String url) {
        return extractVideoId(url) != null;
    }

}