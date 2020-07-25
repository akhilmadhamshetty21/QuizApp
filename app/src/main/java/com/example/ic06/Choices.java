package com.example.ic06;

import java.util.Arrays;

class Choices {
    int answer;
    String[] choice;

    public Choices(int answer, String[] choice) {
        this.answer = answer;
        this.choice = choice;
    }

    public Choices() {
    }

    @Override
    public String toString() {
        return "Choices{" +
                "answer=" + answer +
                ", choice=" + Arrays.toString(choice) +
                '}';
    }
}
