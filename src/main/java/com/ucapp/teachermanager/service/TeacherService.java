package com.ucapp.teachermanager.service;

import com.ucapp.teachermanager.exception.UserNotFoundException;
import com.ucapp.teachermanager.model.Teacher;
import com.ucapp.teachermanager.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeacherService {
    private final TeacherRepo teacherRepo;

    @Autowired
    public TeacherService(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    public Teacher addTeacher(Teacher teacher) {
        return teacherRepo.save(teacher);
    }

    public List<Teacher> findAllTeachers() {
        return teacherRepo.findAll();
    }

    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepo.save(teacher);
    }

    public Optional<Teacher> findTeacherById(long id) {

        return teacherRepo.findById(id);
    }

    public void deletedTeacher(Long id) {
        teacherRepo.deleteTeacherById(id);

    }

}
