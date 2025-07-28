package com.sahil.quiz_service.service;


import com.sahil.quiz_service.feign.QuizInterface;
import com.sahil.quiz_service.model.QuestionWrapper;
import com.sahil.quiz_service.model.Quiz;
import com.sahil.quiz_service.model.Responses;
import com.sahil.quiz_service.repo.QuizDao;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;



    public ResponseEntity<String> createQuiz(String category, int numsQ, String title) {

        List<Integer> questions =quizInterface.getQuestionsForQuiz(category,numsQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionList(questions);
        quizDao.save(quiz);



        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

        Quiz quiz = quizDao.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionList();

        ResponseEntity<List<QuestionWrapper>> questionForUser = quizInterface.getQuestionsFromId(questionIds);


        return questionForUser;
    }

    public ResponseEntity<Integer> calculateresult(Integer id, List<Responses> responses) {

       ResponseEntity<Integer> score =  quizInterface.getScore(responses);

        return  score;


    }

}
