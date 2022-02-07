package com.example.sigapplication;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyQuestion {
    Date date;
    String content;

    public MyQuestion(Date date, String content) {
        this.date = date;
        this.content = content;
    }

    public String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(date);
    }

    public String getContent() {
        return content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
