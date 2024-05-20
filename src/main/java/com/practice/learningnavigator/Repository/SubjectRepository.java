package com.practice.learningnavigator.Repository;

import com.practice.learningnavigator.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
