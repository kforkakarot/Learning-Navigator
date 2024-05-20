package com.practice.learningnavigator.Service;

import com.practice.learningnavigator.Entity.Exam;

import java.util.List;

public interface ExamService {
    List<Exam> getAllExams();
    Exam getExamById(Long id);
    Exam createExam(Exam exam);
    Exam updateExam(Long id, Exam examDetails);
    void deleteExam(Long id);

}
