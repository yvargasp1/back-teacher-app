package com.ucapp.teachermanager.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucapp.teachermanager.model.Teacher;
import com.ucapp.teachermanager.service.TeacherService;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@AutoConfigureMockMvc
public class TeacherResourceTests {

 @Autowired
 private MockMvc mockMvc;

 @MockBean
 private TeacherService teacherService;

 private Teacher teacher;

 @BeforeEach
 void setUp() {

  MockitoAnnotations.openMocks(this);

  long val = 15;
  teacher = new Teacher();
  teacher.setId(val);
  teacher.setName("Carlos");
  teacher.setEmail("c@gmail.com");
  teacher.setPhone("3135644");
  teacher.setJobTitle("Ingeniero de alimentos");

 }

 @Test

 public void getTeacher() throws Exception {

  doReturn(Optional.of(teacher)).when(teacherService).findTeacherById(teacher.getId());
  mockMvc.perform(MockMvcRequestBuilders.get("/teacher/findBy/{id}", teacher.getId())
    .accept(MediaType.APPLICATION_JSON_VALUE))
    .andExpect(MockMvcResultMatchers.status().isOk());
 }

 @Test
 public void getAllTeachers() throws Exception {

  Teacher teacher = new Teacher();
  long val = 1;
  teacher = new Teacher();
  teacher.setId(val);
  teacher.setName("Carlos");
  teacher.setEmail("c@gmail.com");
  teacher.setPhone("3135644");
  teacher.setJobTitle("Ingeniero de alimentos");

  List<Teacher> teachers = new ArrayList<>();
  teachers.add(teacher);

  doReturn(List.of(teachers)).when(teacherService).findAllTeachers();

  ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/teacher/all"));

  result.andExpect(MockMvcResultMatchers.status().isOk());

 }

 @Test
 public void deleteTeacherById() throws Exception {
  Teacher teacher = new Teacher();
  long val = 1;
  teacher = new Teacher();
  teacher.setId(val);
  teacher.setName("Carlos");
  teacher.setEmail("c@gmail.com");
  teacher.setPhone("3135644");
  teacher.setJobTitle("Ingeniero de alimentos");

  doReturn(Optional.of(teacher)).when(teacherService).findTeacherById(teacher.getId());

  mockMvc.perform(MockMvcRequestBuilders.delete("/teacher/delete/{id}", teacher.getId())
    .accept(MediaType.APPLICATION_JSON_VALUE))
    .andExpect(MockMvcResultMatchers.status().isOk());

 }

 @Test
 public void createTeacher() throws Exception {

  Teacher teacher = new Teacher();
  long val = 1;
  teacher = new Teacher();
  teacher.setId(val);
  teacher.setName("Carlos");
  teacher.setEmail("c@gmail.com");
  teacher.setPhone("3135644");
  teacher.setJobTitle("Ingeniero de alimentos");

  Mockito.when(teacherService.addTeacher(Mockito.any(Teacher.class))).thenReturn(teacher);
  mockMvc.perform(MockMvcRequestBuilders.post("/teacher/add")
    .content(asJsonString(teacher))
    .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
    .andExpect(
      MockMvcResultMatchers.status().isCreated());

 }

 public static String asJsonString(final Object obj) {
  try {
   return new ObjectMapper().writeValueAsString(obj);
  } catch (Exception e) {
   throw new RuntimeException(e);
  }
 }
}
