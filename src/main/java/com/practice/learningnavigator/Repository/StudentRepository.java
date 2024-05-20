package com.practice.learningnavigator.Repository;


import com.practice.learningnavigator.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Long> {
}

