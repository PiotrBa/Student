package com.piotr.students.controller;

import com.piotr.students.model.Student;
import com.piotr.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentControler {


    private StudentRepository studentRepository;

    @Autowired
    public StudentControler(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student){ return studentRepository.save(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
         return studentRepository.findById(id)
                 .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        return studentRepository.findById(id)
                .map(student -> {
                    studentRepository.delete(student);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> putStudent(@PathVariable Long id, @RequestBody @Valid Student student){
        return  studentRepository.findById(id)
                .map(studentFromOb -> {
                    studentFromOb.setFirstName(student.getFirstName());
                    studentFromOb.setLastName(student.getLastName());
                    studentFromOb.setEmail(student.getEmail());
                    return ResponseEntity.ok().body(studentRepository.save(studentFromOb));
                }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Student> patchStudent(@PathVariable Long id, @RequestBody Student student){
        return  studentRepository.findById(id)
                .map(studentFromOb -> {
                    if (!StringUtils.hasText(student.getFirstName())) {
                        studentFromOb.setFirstName(student.getFirstName());
                    }
                    if (!StringUtils.hasText(student.getFirstName())) {
                        studentFromOb.setLastName(student.getLastName());
                    }
                    return ResponseEntity.ok().body(studentRepository.save(studentFromOb));
                }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    /*
    @GetMapping("/lastname")

    public List<Student> findStudent (@RequestParam String lastName, @RequestParam int numberOfPage){
        Pageable pageable = PageRequest.of(numberOfPage, 2, Sort.by("firstName"));
        return studentRepository.findByLastName(lastName, pageable);
    }

    @GetMapping("/marian")
    public List<Student> findStudent3 (){
        return studentRepository.findStudentsWithNameMarian();

    }



    /*
    @GetMapping("/hello")
    public String sayHello(){
        return "Witaj";
    }

    @GetMapping("/student")
    public Student getStudent(){
        Student student = new Student();
        student.setFirstName("Arnold");
        student.setLastName("Boczek");
        student.setEmail("boczek@gmail.com");
        return student;
    }
    */
}
