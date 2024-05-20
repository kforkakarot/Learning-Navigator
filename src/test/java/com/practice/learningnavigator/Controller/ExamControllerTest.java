package com.practice.learningnavigator.Controller;

import com.practice.learningnavigator.Entity.Exam;
import com.practice.learningnavigator.Service.ExamServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExamControllerTest {

    @Mock
    private ExamServiceImpl examService;

    @InjectMocks
    private ExamController examController;

    private MockMvc mockMvc;

    @Test
    void getAllExams() throws Exception {
        mockMvc = standaloneSetup(examController).build();

        when(examService.getAllExams()).thenReturn(Collections.singletonList(new Exam()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/exams")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }

    @Test
    void deleteExamById() {
        doNothing().when(examService).deleteExam(1L);

        ResponseEntity<Void> responseEntity = examController.deleteExam(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(examService, times(1)).deleteExam(1L);
    }

    @Test
    void getExamById() throws Exception {
        mockMvc = standaloneSetup(examController).build();
        Exam exam = new Exam();
        exam.setId(1L);
        when(examService.getExamById(1L)).thenReturn(exam);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/exams/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }


}

