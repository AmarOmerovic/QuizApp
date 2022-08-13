package com.amaromerovic.neverhaveiever.model;

public class Question {
    private int questionNumber;
    private int questionText;
    private boolean answer;

    public Question(int questionNumber, int questionText, boolean answer) {
        this.questionNumber = questionNumber;
        this.questionText = questionText;
        this.answer = answer;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public int getQuestionText() {
        return questionText;
    }

    public boolean getAnswer() {
        return answer;
    }
}
