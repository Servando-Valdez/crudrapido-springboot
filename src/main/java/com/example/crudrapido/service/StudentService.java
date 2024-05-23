package com.example.crudrapido.service;

import com.example.crudrapido.dto.StudentDTO;
import com.example.crudrapido.entity.Student;
import com.example.crudrapido.entity.Teacher;
import com.example.crudrapido.exceptionAdvice.DuplicateEmailException;
import com.example.crudrapido.exceptionAdvice.IdNotFoundException;
import com.example.crudrapido.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherService teacherService;

    public List<StudentDTO> getStudents(){
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private StudentDTO convertToDto(Student student) {
        StudentDTO dto = new StudentDTO(
                student.getStudentId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getTeacher().getTeacherId()
        );
        return dto;
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Student not found with id: " + id));
    }

//    create and update in a method
    public void saveOrUpdate(StudentDTO studentDTO){
        //validate email
        String email = studentDTO.getEmail();
        if(this.studentRepository.existsByEmail(email)){
            throw new DuplicateEmailException("email already exists");

        }
        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(email);
        Teacher teacher = this.teacherService.getTeacherById(studentDTO.getTeacher());
        student.setTeacher(teacher);

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
