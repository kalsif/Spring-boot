package com.example.esercizio11;

import static org.assertj.core.api.Assertions.assertThat;
import com.example.esercizio11.entities.Student;
import com.example.esercizio11.repositories.StudentRepository;
import com.example.esercizio11.servicies.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "test")
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    private Student createStudent(){
        Student student = new Student();
        student.setName("Anto");
        student.setSurname("Troiano");
        student.setWorking(false);

        return studentRepository.saveAndFlush(student);
    }

    @Test
    void createStudentTest() throws Exception{
        Student student = new Student();
        student.setName("Anto");
        student.setSurname("Troiano");
        student.setWorking(false);

        Student studentDB = studentRepository.save(student);
        assertThat(studentDB).isNotNull();
        assertThat(studentService.createStudent(student)).isEqualTo(studentDB);
    }

    @Test
    void getStudentsTest() throws Exception{
        createStudent();


        assertThat(studentRepository.findAll().size()).isNotZero();
        assertThat(studentService.getAllStudents().contains(createStudent()));
    }

    @Test
    void getByIdTest(){
        Student student = new Student();
        student.setName("Anto");
        student.setSurname("Troiano");
        student.setWorking(false);

        studentRepository.save(student);
        Student studentFind = studentRepository.findById(student.getId()).get();
        assertThat(studentFind.getId()).isNotNull();
        assertThat(studentFind.getId()).isEqualTo(student.getId());
        assertThat(studentService.getStudentById(student.getId())).isEqualTo(student);
    }

    @Test
    void getByIdInvalidTest(){
        Student student = new Student(1,"anto","anto",true);


        studentRepository.save(student);
        Student studentFind = studentRepository.findById(student.getId()).get();
        assertThat(studentFind.getId()).isNotNull();
        assertThat(studentFind.getId()).isEqualTo(student.getId());
        assertThat(studentService.getStudentById(student.getId())).isEqualTo(student);
    }

    @Test
    void deleteStudentTest(){
        Student student = new Student();
        student.setName("Anto");
        student.setSurname("Troiano");
        student.setWorking(false);

        studentRepository.saveAndFlush(student);
        assertThat(student.getId()).isNotNull();
        assertThat(studentService.deleteStudentById(student.getId())).isEqualTo(student);
        assertThat(studentService.getAllStudents().size()).isZero();
    }

    @Test
    void updateStatusStudent(){
        Student student = new Student();
        student.setName("Anto");
        student.setSurname("Troiano");
        student.setWorking(false);

        studentRepository.saveAndFlush(student);
        studentService.updateStatus(1,true);

        assertThat(studentService.getStudentById(1).isWorking()).isEqualTo(true);
        assertThat(studentService.updateStatus(1,true)).isNotNull();
    }

}
