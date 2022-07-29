package com.example.Look_back;

public class DiaryModel {

    private String date;
    private String diary;

    public DiaryModel(String date, String diary) {
        this.date = date;
        this.diary = diary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDiary() {
        return diary;
    }

    public void setDiary(String diary) {
        this.diary = diary;
    }
}
