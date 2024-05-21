package com.example.crudrapido.service;

import com.example.crudrapido.entity.Student;
import com.example.crudrapido.exceptionAdvice.IdNotFoundException;
import com.example.crudrapido.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherService teacherService;

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Student not found with id: " + id));
    }

//    create and update in a method
    public void saveOrUpdate(Student student){
        System.out.println(student.getTeacher().getTeacherId());
        this.teacherService.getTeacherById(student.getTeacher().getTeacherId());
        studentRepository.save(student);
    }

    public void update(Student student, Long id){
        // Validar si el id y el objeto student no son nulos
        if (student == null || id == null) {
            System.out.println("Student or id cannot be null");
            throw new IllegalArgumentException("Student or id cannot be null");
        }
        Student currentStudent = this.getStudent(id);
        currentStudent.setEmail(student.getEmail());
        currentStudent.setLastName(student.getLastName());
        currentStudent.setFirstName(student.getFirstName());
        this.studentRepository.save(currentStudent);
    }

    //delte by id
    public void delete(Long id){
        Student student = this.getStudent(id);

        studentRepository.delete(student);
    }
}
