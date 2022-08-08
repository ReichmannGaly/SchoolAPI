package school.hei.schoolapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.hei.schoolapi.model.Groups;
import school.hei.schoolapi.repository.GroupRepository;
import school.hei.schoolapi.repository.StudentRepository;
import school.hei.schoolapi.model.Student;
import java.util.Optional;

import java.util.Collections;
import java.util.List;

@RestController
public class SchoolAPIController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GroupRepository groupRepository;

    @GetMapping({"/students/{id}", "/students"})
    public List<Student> findAllStudents(@PathVariable(required = false) Long id){
        if (id == null){
            return studentRepository.findAll();
        }
        else{
            return studentRepository.findAllById(Collections.singleton(id));
        }

    }

    @PostMapping("/students")
    public List<Student> create(@RequestBody Student s){
        studentRepository.save(s);
        return studentRepository.findAll();
    }

    @PutMapping("/students/{id}")
    public List<Student> put(@PathVariable Long id,@RequestBody Student s){
        Optional<Student> studentData = studentRepository.findById(id);
        if (studentData.isPresent()) {
            Student student = studentData.get();
            student.setName(s.getName());
            student.setGroup(s.getGroup());
            studentRepository.save(student);
            return studentRepository.findAll();
        } else {
            return null;
        }
    }

    @DeleteMapping("/students/{id}")
    public void delete(@PathVariable Long id){
        studentRepository.deleteById(id);
    }

    @GetMapping("/groups")
    public List<Groups> findAllGroups(){
        return groupRepository.findAll();
    }
}