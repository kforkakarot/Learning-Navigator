package com.practice.learningnavigator.Service;

import com.practice.learningnavigator.Entity.Exam;
import com.practice.learningnavigator.Entity.Student;
import com.practice.learningnavigator.Entity.Subject;
import com.practice.learningnavigator.Exceptions.ResourceNotFoundException;
import com.practice.learningnavigator.Repository.ExamRepository;
import com.practice.learningnavigator.Repository.StudentRepository;
import com.practice.learningnavigator.Repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

    @Override
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = getStudentById(id);
        student.setName(studentDetails.getName());
        student.setSubjects(studentDetails.getSubjects());
        student.setExams(studentDetails.getExams());
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }

    @Override
    public Student registerForExam(Long studentId, Long examId) {
        Student student = getStudentById(studentId);
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found with id " + examId));
        Subject subject = exam.getSubject();
        if (!student.getSubjects().contains(subject)) {
            throw new IllegalArgumentException("Student must be enrolled in the subject before registering for the exam");
        }
        student.getExams().add(exam);
        return studentRepository.save(student);
    }

}
