package com.practice.learningnavigator.Service;

import com.practice.learningnavigator.Entity.Exam;
import com.practice.learningnavigator.Exceptions.ResourceNotFoundException;
import com.practice.learningnavigator.Repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements  ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public Exam getExamById(Long id) {
        return examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found with id " + id));
    }

    @Override
    public Exam updateExam(Long id, Exam examDetails) {
        Exam exam = getExamById(id);
        exam.setSubject(examDetails.getSubject());
        return examRepository.save(exam);
    }

    @Override
    public void deleteExam(Long id) {
        Exam exam = getExamById(id);
        examRepository.delete(exam);
    }
}

