package com.sahil.question_service.service;


import com.sahil.question_service.model.Question;
import com.sahil.question_service.model.QuestionWrapper;
import com.sahil.question_service.model.Responses;
import com.sahil.question_service.repo.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {


    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed Request", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {


        List<Integer> questionList = questionDao.findRandomQuestionByCategory(categoryName,numQuestions);

        return new ResponseEntity<>(questionList, HttpStatus.OK);

    }


    public ResponseEntity<List<QuestionWrapper>> getAllQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer id : questionIds){
            questions.add(questionDao.findById(id).get());
        }

        for(Question question : questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
           // wrapper.getQuestionTitle(question.getQuestionTitle()); // error line i did getQuestionTitle instead of SetQuestionTitle
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption1(question.getOption1());

            wrappers.add(wrapper);



        }

        return new ResponseEntity<>(wrappers,HttpStatus.OK);


    }

    public ResponseEntity<Integer> getScore(List<Responses> responses) {

        int right = 0;

        for(Responses responses1 : responses){
            Question question = questionDao.findById(responses1.getId()).get();
            if(responses1.getResponse().equals(question.getRightAnswer()));
                right++;


        }

        return  new ResponseEntity<>(right, HttpStatus.OK);
        //Why not use == for strings?
        //== checks if two references point to the exact same object in memory.
        //.equals() checks if the content is the same.


    }
}
