package com.example.mp3player;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import com.example.mp3player.Playlist;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvHeader;
    MaterialButton btnPlaylist1, btnPlaylist2, btnPlaylist3, btnPlaylist4, btnPlaylist5, btnPlaylist6, btnPlaylist7, btnPlaylist8, btnPlaylist9;
    MaterialButton btnMenu, btnBack, btnNext, btnPlay, btnAddMp3, btnRemove;
    MaterialButton btnExit;

    Boolean isPlaylist1Null, isPlaylist2Null, isPlaylist3Null, isPlaylist4Null, isPlaylist5Null, isPlaylist6Null, isPlaylist7Null, isPlaylist8Null, isPlaylist9Null;
    Playlist playList1, playList2, playList3, playList4, playList5, playList6, playList7, playList8, playList9;

    String currentDisplayScene;
    Integer currentPlayingMusicNumber, currentPlayingPlaylistNumber, currentPlayingPlaylistLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentDisplayScene = "Menu";
        currentPlayingPlaylistNumber = 1;
        currentPlayingMusicNumber = 1;
        currentPlayingPlaylistLength = 0;
        LoadAllPlaylists();

        tvHeader = findViewById(R.id.tvHeader);
        LoadCurrentSceneButtons();
        assignBtnId(btnExit, R.id.btnExit);
    }

    void LoadAllPlaylists() {
        isPlaylist1Null = true;
        isPlaylist2Null = true;
        isPlaylist3Null = true;
        isPlaylist4Null = true;
        isPlaylist5Null = true;
        isPlaylist6Null = true;
        isPlaylist7Null = true;
        isPlaylist8Null = true;
        isPlaylist9Null = true;
    }

    void LoadCurrentSceneButtons() {
        if (currentDisplayScene.equals("Menu")) {
            if (btnPlaylist1 != null)
                return;
            assignBtnId(btnPlaylist1, R.id.btnPlaylist1);
            assignBtnId(btnPlaylist2, R.id.btnPlaylist2);
            assignBtnId(btnPlaylist3, R.id.btnPlaylist3);
            assignBtnId(btnPlaylist4, R.id.btnPlaylist4);
            assignBtnId(btnPlaylist5, R.id.btnPlaylist5);
            assignBtnId(btnPlaylist6, R.id.btnPlaylist6);
            assignBtnId(btnPlaylist7, R.id.btnPlaylist7);
            assignBtnId(btnPlaylist8, R.id.btnPlaylist8);
            assignBtnId(btnPlaylist9, R.id.btnPlaylist9);
        } else if (currentDisplayScene.equals("Playlist")) {
            if (btnMenu != null)
                return;
            assignBtnId(btnMenu, R.id.btnMenu);
            assignBtnId(btnNext, R.id.btnNext);
            assignBtnId(btnBack, R.id.btnBack);
            assignBtnId(btnPlay, R.id.btnPlay);
            assignBtnId(btnAddMp3, R.id.btnAddMp3);
            assignBtnId(btnRemove, R.id.btnRemove);
        }
    }

    void assignBtnId(MaterialButton btn, int id) {
        btn = (MaterialButton) findViewById(id);
        btn.setOnClickListener(this);
    }

    void UpdateXmlScene() {
        if (currentDisplayScene.equals("Menu")) {
            setContentView(R.layout.activity_main);
        } else if (currentDisplayScene.equals("Playlist")) {
            setContentView(R.layout.playlist_layout);
        }
        LoadCurrentSceneButtons();
    }

    void UpdateCurrentPlayingMusic() {
        Boolean pass = true;
    }

    void StopPlayingMusic() {
        Boolean pass = true;
    }

    void StartPlayingMusic() {
        Boolean pass = true;
    }

    void AddMp3ToCurrentPlaylist() {
        Boolean pass = true;
    }

    void RemoveCurrentMusic() {
        Boolean pass = true;
    }

    void GoToPlaylist(int playlistNumber) {
        currentDisplayScene = "Playlist";
        currentPlayingPlaylistNumber = playlistNumber;
        UpdateXmlScene();
    }

    Integer ConvertPlayListNameToNumber(String name) {
        Character lastLetter = name.charAt(name.length() - 1);
        Integer playlistNumber = Character.getNumericValue(lastLetter);
        return playlistNumber;
    }

    @Override
    public void onClick(View view) {
        MaterialButton btn = (MaterialButton) view;
        String btnText = btn.getText().toString();

        if (btnText.equals("Exit")) {
            System.exit(0);
        }

        LoadCurrentSceneButtons();

        if (currentDisplayScene.equals("Menu")) {
            GoToPlaylist(ConvertPlayListNameToNumber(btnText));
        } else if (currentDisplayScene.equals("Playlist")) {
            if (btnText.equals("Next")) {
                if (currentPlayingMusicNumber < currentPlayingPlaylistLength) {
                    StopPlayingMusic();
                    currentPlayingMusicNumber++;
                    UpdateCurrentPlayingMusic();
                }
            } else if (btnText.equals("Back")) {
                if (currentPlayingMusicNumber > 1) {
                    StopPlayingMusic();
                    currentPlayingMusicNumber--;
                    UpdateCurrentPlayingMusic();
                }
            } else if (btnText.equals("Menu")) {
                StopPlayingMusic();
                currentDisplayScene = "Menu";
                UpdateXmlScene();
            } else if (btnText.equals("Play")) {
                StartPlayingMusic();
                btn.setText("Stop");
            } else if (btnText.equals("Stop")) {
                StopPlayingMusic();
                btn.setText("Play");
            } else if (btnText.equals("Add Mp3")) {
                AddMp3ToCurrentPlaylist();
            } else if (btnText.equals("Remove")) {
                RemoveCurrentMusic();
            }
        }
    }
}