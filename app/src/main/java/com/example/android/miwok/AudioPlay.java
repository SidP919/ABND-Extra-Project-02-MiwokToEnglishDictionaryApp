package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlay {

    public static MediaPlayer mediaPlayer;
    public static boolean isplayingAudio = false;
//    private static SoundPool soundPool; //Deprecated API

    public static void playAudio(Context c, int id) {
        releaseMediaPlayer();
        mediaPlayer = MediaPlayer.create(c, id);
//        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            isplayingAudio = true;
        }
    }

    public static void changeSong(Context c, int id) {
        releaseMediaPlayer();
        isplayingAudio = false;
        playAudio(c, id);
    }

    public static void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
