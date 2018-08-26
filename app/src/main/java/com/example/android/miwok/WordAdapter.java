package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private TextView miwokWord;
    private TextView engWord;
    private ImageView imageView;
    private int bgColor;

    public WordAdapter(@NonNull Context context, ArrayList<Word> resource, int bgcolor) {
        super(context, 0, resource);
        this.bgColor = bgcolor;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(
                    R.layout.numbers_main_custom_view, parent, false);
        }
        View textContainer = listView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), bgColor);
        textContainer.setBackgroundColor(color);
        miwokWord = listView.findViewById(R.id.miwok_word);
        engWord = listView.findViewById(R.id.eng_word);
        imageView = listView.findViewById(R.id.word_ImageView);
        imageView.setClickable(true);
        LinearLayout linearLayout = listView.findViewById(R.id.word_LinearLayout);
        linearLayout.setClickable(true);

        Word w = getItem(position);
        miwokWord.setText(w.getDefaultTrans());
        engWord.setText(w.getMiwokTrans());
        if (w.getImageId() != 0) {
            imageView.setImageResource(w.getImageId());
        }
        return listView;
    }
}
