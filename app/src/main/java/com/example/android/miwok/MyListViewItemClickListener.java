package com.example.android.miwok;

import android.view.View;
import android.widget.AdapterView;

public class MyListViewItemClickListener implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Word w = (Word) parent.getItemAtPosition(position);
//        Toast.makeText(view.getContext()
//                , w.getMiwokTrans()
//                , Toast.LENGTH_SHORT).show();
        if (!AudioPlay.isplayingAudio) {
            AudioPlay.playAudio(view.getContext(), w.getAudioResourceId());
        } else if (AudioPlay.isplayingAudio) {
            AudioPlay.changeSong(view.getContext(), w.getAudioResourceId());
        }
    }
}
