package com.sahil.quiz_service.controllers;


import com.sahil.quiz_service.model.QuestionWrapper;
import com.sahil.quiz_service.model.QuizDto;
import com.sahil.quiz_service.model.Responses;
import com.sahil.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizdto){

        return quizService.createQuiz(quizdto.getCategoryName(),quizdto.getNumQuestions(),quizdto.getTitle());

    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){

        return  quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,
                                              @RequestBody List<Responses> responses){

        return quizService.calculateresult(id, responses);

    }


}
