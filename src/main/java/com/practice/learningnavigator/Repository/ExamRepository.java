package com.practice.learningnavigator.Repository;


import com.practice.learningnavigator.Entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExamRepository extends JpaRepository<Exam, Long> {
}
