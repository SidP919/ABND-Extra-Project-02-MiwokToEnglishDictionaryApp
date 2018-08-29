package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.view.View;
import android.widget.AdapterView;

public class MyListViewItemClickListener implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Word w = (Word) parent.getItemAtPosition(position);
//        Toast.makeText(view.getContext()
//                , w.getMiwokTrans()
//                , Toast.LENGTH_SHORT).show();
        // Create and setup the {@link AudioManager} to request audio focus
        AudioPlay.mAudioManager = (AudioManager) view.getContext().getSystemService(Context.AUDIO_SERVICE);
        AudioPlay audioPlay = new AudioPlay(view.getContext(), w.getAudioResourceId());
    }
}
