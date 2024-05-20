package com.practice.learningnavigator.Service;

import com.practice.learningnavigator.Entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    Student createStudent(Student student);
    Student updateStudent(Long id, Student studentDetails);
    void deleteStudent(Long id);
    Student registerForExam(Long studentId, Long examId);

}
