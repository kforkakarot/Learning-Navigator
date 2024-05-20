package com.practice.learningnavigator.Service;

import com.practice.learningnavigator.Entity.Subject;
import com.practice.learningnavigator.Exceptions.ResourceNotFoundException;
import com.practice.learningnavigator.Repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubjectServiceImpl implements  SubjectService{

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id " + id));
    }

    @Override
    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }

    @Override
    public Subject updateSubject(Long id, Subject subjectDetails) {
        Subject subject = getSubjectById(id);
        subject.setName(subjectDetails.getName());
        return subjectRepository.save(subject);
    }

    @Override
    public void deleteSubject(Long id) {
        Subject subject = getSubjectById(id);
        subjectRepository.delete(subject);
    }

}
