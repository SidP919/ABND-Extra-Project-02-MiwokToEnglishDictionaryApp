package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

public class AudioPlay {

    public static MediaPlayer mediaPlayer;
    public static boolean isplayingAudio = false;
//    private static SoundPool soundPool; //Deprecated API


    private final static String TAG = "Audio Player:--";
    /**
     * Handles audio focus when playing a sound file
     */
    public static AudioManager mAudioManager;
    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    public AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {// The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.
                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                Log.d(TAG, "focus Lost Transient");
                mediaPlayer.pause();
                isplayingAudio = false;
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                AudioPlay.mediaPlayer.start();
                isplayingAudio = true;
                Log.d(TAG, "focus Gained");
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
                Log.d(TAG, "focus Lost");
            }
        }
    };

    public AudioPlay(Context context, int id) {

        assert mAudioManager != null;
        Log.d(TAG, "" + mAudioManager.toString());

        // Request audio focus so in order to play the audio file. The app needs to play a
        // short audio file, so we will request audio focus with a short amount of time
        // with AUDIOFOCUS_GAIN_TRANSIENT.
        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        Log.d(TAG, "" + result);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            // We have audio focus now.
            Log.d(TAG, "Inside If: " + result);
            if (!isplayingAudio) {
                playAudio(context, id);
            } else {
                changeAudio(context, id);
            }
        }
    }

    private void playAudio(Context c, int id) {
        Log.d(TAG, "playAudio Entered");

        releaseMediaPlayer();
        mediaPlayer = MediaPlayer.create(c, id);
//        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);

        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            isplayingAudio = true;
        }
    }

    private void changeAudio(Context c, int id) {
        Log.d(TAG, "changeAudio Entered");
        releaseMediaPlayer();
        isplayingAudio = false;
        playAudio(c, id);
    }

    public void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            isplayingAudio = false;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
            Log.d(TAG, "mediaPlayer released");
        }
    }
}

