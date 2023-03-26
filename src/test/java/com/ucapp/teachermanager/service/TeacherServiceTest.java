package com.ucapp.teachermanager.service;

import com.ucapp.teachermanager.model.Teacher;
import com.ucapp.teachermanager.repo.TeacherRepo;

import ch.qos.logback.core.status.Status;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TeacherServiceTest {
    AutoCloseable openMocks;

    @Mock
    private TeacherRepo teacherRepo;

    @InjectMocks
    private TeacherService teacherService;
    @Autowired
    private MockMvc mockMvc;

    private Teacher teacher;

    Optional<Teacher> teacherOptional;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        long val = 15;
        teacher = new Teacher();
        teacher.setId(val);
        teacher.setName("Carlos");
        teacher.setEmail("c@gmail.com");
        teacher.setPhone("3135644");
        teacher.setJobTitle("Ingeniero de alimentos");

    }

    @Test 
    void saveTeacher() throws Exception
    {
    when(teacherRepo.save(Mockito.any(Teacher.class))).thenReturn(teacher);
    assertNotNull(teacherService.addTeacher(new Teacher()));

    }

    @Test
    void deleteById() {

        when(teacherRepo.findById(teacher.getId())).thenReturn(teacherOptional);

        teacherService.deletedTeacher(teacher.getId()) ;

        assertNull(teacherService.findTeacherById(teacher.getId()));
    }

    @Test
    void updateTeacher(){
        when(teacherRepo.save(Mockito.any(Teacher.class))).thenReturn(teacher);
        teacher.setPhone("5645645646");
        assertNotNull(teacherService.updateTeacher(teacher));

    }

    @Test
    void findAllTeachers() {
        when(teacherRepo.findAll()).thenReturn(Collections.singletonList(teacher));
        assertNotNull(teacherService.findAllTeachers());
    }

    @AfterEach
    void tearDown() throws Exception {
        // my tear down code...
        openMocks.close();
    }
}