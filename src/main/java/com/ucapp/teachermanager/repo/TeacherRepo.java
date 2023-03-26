package com.ucapp.teachermanager.repo;

import com.ucapp.teachermanager.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepo extends JpaRepository <Teacher,Long> {

    void deleteTeacherById(Long id);

    Optional<Teacher> findTeacherById(long id);
}
