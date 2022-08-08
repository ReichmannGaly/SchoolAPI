package school.hei.schoolapi.repository;

import school.hei.schoolapi.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentSimpleRepository {
    List<Student> findAll(); //select *

    Student save(Student s); //insert

    Optional<Student> findById(Long id);

    void deleteById(Long Id); //  delete

    List<Student> findWhereNameLike(String query);
    // select * from student where name like '%query%'
}
// Creer une inplementation de cette classe
// Creer une autre implementation en utilisant JPA avec JPQL (Java Persistance Query Language)
// Cherher une annotation qui s'appelle @Query