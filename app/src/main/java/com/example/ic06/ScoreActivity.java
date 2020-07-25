package com.example.ic06;

public class ScoreActivity {
    public int score=0;

    public ScoreActivity() {
    }

    public ScoreActivity(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "ScoreActivity{" +
                "score=" + score +
                '}';
    }

    public void setScore(int score) {
        this.score = score;
    }
}
