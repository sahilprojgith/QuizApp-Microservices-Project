package com.sahil.quiz_service.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

//    @ManyToMany

    @ElementCollection
    private List<Integer> questionList;


    public Quiz(int id, String title, List<Integer> questionList) {
        this.id = id;
        this.title = title;
        this.questionList = questionList;
    }

    public Quiz() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Integer> questionList) {
        this.questionList = questionList;
    }

}
