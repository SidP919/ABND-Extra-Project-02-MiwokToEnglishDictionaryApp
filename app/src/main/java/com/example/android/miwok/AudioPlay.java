package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class AudioPlay {

    public static MediaPlayer mediaPlayer;
    public static boolean isplayingAudio = false;
    private static SoundPool soundPool;

    public static void playAudio(Context c, int id) {
        mediaPlayer = MediaPlayer.create(c, id);
//        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            isplayingAudio = true;
        }
    }

    public static void changeSong(Context c, int id) {
        mediaPlayer.stop();
        mediaPlayer.release();
        isplayingAudio = false;
        playAudio(c, id);
    }
}
