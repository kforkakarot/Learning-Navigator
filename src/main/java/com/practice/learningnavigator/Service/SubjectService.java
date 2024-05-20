package com.practice.learningnavigator.Service;

import com.practice.learningnavigator.Entity.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getAllSubjects();
    Subject getSubjectById(Long id);
    Subject createSubject(Subject subject);
    Subject updateSubject(Long id, Subject subjectDetails);
    void deleteSubject(Long id);
}
