package ru.gb.lesson7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.lesson7.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
