package com.example.ic06;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Arrays;

public class Questions implements Serializable {
    String question;
    String url;
    String[] choices;
    String answer;
    int id;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Questions() {
    }

    @Override
    public String toString() {
        return "Questions{" +
                "question='" + question + '\'' +
                ", url='" + url + '\'' +
                ", choices=" + Arrays.toString(choices) +
                ", answer='" + answer + '\'' +
                ", id=" + id +
                '}';
    }
}