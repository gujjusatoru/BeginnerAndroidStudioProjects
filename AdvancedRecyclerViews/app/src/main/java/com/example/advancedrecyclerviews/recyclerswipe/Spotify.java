package com.example.advancedrecyclerviews.recyclerswipe;

public class Spotify {
    private String nameSong;
    private String songBy;
    private int albumImg;
    private boolean checked;

    public Spotify(String nameSong, String songBy, int albumImg) {
        this.nameSong = nameSong;
        this.songBy = songBy;
        this.albumImg = albumImg;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getSongBy() {
        return songBy;
    }

    public void setSongBy(String songBy) {
        this.songBy = songBy;
    }

    public int getAlbumImg() {
        return albumImg;
    }

    public void setAlbumImg(int albumImg) {
        this.albumImg = albumImg;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
