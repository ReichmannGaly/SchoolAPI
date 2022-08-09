package school.hei.schoolapi.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.hei.schoolapi.model.Student;
import school.hei.schoolapi.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepository repository;

    public Student save(Student s){
        return repository.save(s);
    }

    public List<Student> findAll(){
        return repository.findAll();
    }

    public Optional<Student> findById(Long id){
        return repository.findById(id);
    }

    public List<Student> findByName(String wanted){
        return repository.findByNameContaining(wanted);
    }

    public Student updateAll(Long id,Student s){
        Optional<Student> studentOptional = repository.findById(id);
        if(studentOptional.isPresent()){
            Student student = studentOptional.get();
            student.setName(s.getName());
            student.setGroup(s.getGroup());
            return repository.save(student);
        }
        else {
            throw new NullPointerException("Student not found");
        }
    }

    public Student updateName(Long id, String newName){
        Optional<Student> studentOptional = repository.findById(id);
        if(studentOptional.isPresent()){
            Student student = studentOptional.get();
            student.setName(newName);
            return repository.save(student);
        }
        else {
            throw new NullPointerException("Student not found");
        }
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
