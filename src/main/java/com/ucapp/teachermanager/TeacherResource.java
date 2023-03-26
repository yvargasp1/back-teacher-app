package com.ucapp.teachermanager;

import com.ucapp.teachermanager.model.Teacher;
import com.ucapp.teachermanager.service.TeacherService;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teacher")
@Transactional
public class TeacherResource {
    private final TeacherService teacherService;

    public TeacherResource(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Teacher>> getAllTeacher() {
        List<Teacher> teachers = teacherService.findAllTeachers();
        return new ResponseEntity<>(
                teachers, HttpStatus.OK);

    }

    @GetMapping("/findBy/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherService.findTeacherById(id);
        return ResponseEntity.of(teacher);
    }

    @PostMapping("/add")
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
        Teacher newTeacher = teacherService.addTeacher(teacher);
        return new ResponseEntity<>(
                newTeacher, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Teacher> updateTeacher(@RequestBody Teacher teacher) {
        Teacher newTeacher = null;
        System.err.println("Mi empleado" + teacher);
        try {
            newTeacher = teacherService.updateTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(
                newTeacher, HttpStatus.OK);
    }

    @PutMapping("/update-photo/{id}")
    public ResponseEntity<?> updatePhoto(@ModelAttribute Teacher Teacher, @RequestParam MultipartFile archivo,
            @PathVariable Long id) throws IOException {
        Optional<Teacher> optional = teacherService.findTeacherById(id);

        Teacher newTeacher = optional.get();

        if (!archivo.isEmpty()) {
            newTeacher.setImageUrl(archivo.getBytes());
        }

        newTeacher = teacherService.updateTeacher(newTeacher);
        return new ResponseEntity<>(
                newTeacher, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable("id") Long id) {
        teacherService.deletedTeacher(id);
        return new ResponseEntity<>(
                HttpStatus.OK);
    }

    @PostMapping("/create-photo")
    public ResponseEntity<?> createPhoto(@ModelAttribute Teacher Teacher, @RequestParam MultipartFile archivo)
            throws IOException {
        System.err.println("mi profesor" + Teacher);
        System.err.println("mi archivo" + archivo);
        if (!archivo.isEmpty()) {
            Teacher.setImageUrl(archivo.getBytes());
        }

        Teacher newTeacher = teacherService.addTeacher(Teacher);
        return new ResponseEntity<>(
                newTeacher, HttpStatus.CREATED);

    }

    @GetMapping("/uploads/img/{id}")
    public ResponseEntity<?> verImagen(@PathVariable Long id) {
        Optional<Teacher> optional = teacherService.findTeacherById(id);
        if (!optional.isPresent() || optional.get().getImageUrl() == null) {
            return ResponseEntity.notFound().build();
        }
        Resource img = new ByteArrayResource(optional.get().getImageUrl());
        System.err.println(img);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(img);
    }
}
