package com.piotr.students.repository;

import com.piotr.students.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /*
    List<Student> findByLastName(String lastname, Pageable pageable);


    @Query("Select s from Student s where s.firstName = 'Marian' ")
    List<Student> findStudentsWithNameMarian();
    */


}
