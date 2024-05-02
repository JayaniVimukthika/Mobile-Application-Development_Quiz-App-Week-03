package com.jayani.quizapp;

public class questionModel {
    String question;
    String optionA;
    String optionB;
    String optionC;
    String optionD;
    String answer;

    public questionModel(String question, String optionA, String optionB, String optionC, String optionD, String answer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getoptionA() {
        return optionA;
    }

    public void setoptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getoptionB() {
        return optionB;
    }

    public void setoptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getoptionC() {
        return optionC;
    }

    public void setoptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getoptionD() {
        return optionD;
    }

    public void setoptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getAns() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

