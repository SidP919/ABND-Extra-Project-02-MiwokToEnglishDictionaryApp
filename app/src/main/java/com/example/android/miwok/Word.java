package com.example.android.miwok;

public class Word {
    private String miwokTrans;
    private String engTrans;
    private int imageResourceId;
    private int audioResourceId;

    public Word(String m, String e, int i, int a) {
        this.miwokTrans = m;
        this.engTrans = e;
        this.imageResourceId = i;
        this.audioResourceId = a;
    }

    public Word(String m, String e, int a) {
        this.miwokTrans = m;
        this.engTrans = e;
        this.imageResourceId = 0;
        this.audioResourceId = a;
    }

    public String getDefaultTrans() {
        return this.engTrans;
    }

    public String getMiwokTrans() {
        return this.miwokTrans;
    }

    public int getImageId() {
        return imageResourceId;
    }

    public int getAudioResourceId() {
        return audioResourceId;
    }
}
