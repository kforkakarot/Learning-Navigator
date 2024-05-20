package com.practice.learningnavigator.Controller;

import com.practice.learningnavigator.Entity.Exam;
import com.practice.learningnavigator.Entity.Student;
import com.practice.learningnavigator.Entity.Subject;
import com.practice.learningnavigator.Exceptions.ResourceNotFoundException;
import com.practice.learningnavigator.Service.ExamServiceImpl;
import com.practice.learningnavigator.Service.StudentServiceImpl;
import com.practice.learningnavigator.Service.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamServiceImpl examService;

    @Autowired
    private SubjectServiceImpl subjectService;

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long id) {
        Exam exam = examService.getExamById(id);
        return ResponseEntity.ok(exam);
    }

    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        // Validate that the subject exists
        Subject subject = subjectService.getSubjectById(exam.getSubject().getId());
        exam.setSubject(subject);

        Exam createdExam = examService.createExam(exam);
        return ResponseEntity.status(201).body(createdExam);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long id, @RequestBody Exam examDetails) {
        // Validate that the subject exists
        Subject subject = subjectService.getSubjectById(examDetails.getSubject().getId());
        examDetails.setSubject(subject);

        Exam updatedExam = examService.updateExam(id, examDetails);
        return ResponseEntity.ok(updatedExam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{examId}/register-student/{studentId}")
    public ResponseEntity<Exam> registerStudentForExam(@PathVariable Long examId, @PathVariable Long studentId) {
        Exam exam = examService.getExamById(examId);
        Student student = studentService.getStudentById(studentId);

        // Check if the student is enrolled in the subject of the exam
        if (!student.getSubjects().contains(exam.getSubject())) {
            throw new ResourceNotFoundException("Student not enrolled in the subject of the exam.");
        }

        exam.getStudents().add(student);
        student.getExams().add(exam);

        examService.createExam(exam); // Save the updated exam with the new student
        studentService.createStudent(student); // Save the updated student with the new exam

        return ResponseEntity.ok(exam);
    }
}

