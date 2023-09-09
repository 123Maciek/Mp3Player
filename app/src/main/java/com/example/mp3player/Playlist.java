package com.example.mp3player;

import java.util.List;

public class Playlist {
    private Integer Length;
    private List<String> Mp3Path;
    private Integer Number;

    public Playlist(Integer number, List<String> mp3Path)
    {
        Mp3Path = mp3Path;
        Length = Mp3Path.size();
        Number = number;
    }
}
