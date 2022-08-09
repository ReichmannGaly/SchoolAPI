package school.hei.schoolapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import school.hei.schoolapi.model.Groups;
import school.hei.schoolapi.repository.GroupRepository;
import school.hei.schoolapi.repository.StudentRepository;
import school.hei.schoolapi.model.Student;
import school.hei.schoolapi.service.StudentService;

import java.util.Optional;

import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
public class SchoolAPIController {

    private GroupRepository groupRepository;

    private StudentService service;

    @GetMapping( "/students")
    public List<Student> findAllStudents(){
        return service.findAll();
    }

    @GetMapping("/students/{id}")
    public Optional<Student> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping("/students/search")
    public List<Student> findByName(@RequestParam(name = "query") String name){
        return service.findByName(name);
    }

    @PostMapping("/students")
    public Student create(@RequestBody Student s){
        return service.save(s);
    }

    @PutMapping("/students/{id}")
    public Student put(@PathVariable Long id,@RequestBody Student s){
        return service.updateAll(id,s);
    }

    @PatchMapping("/students")
    public Student updateName(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "newName") String newName){
            return service.updateName(id,newName);
    }

    @DeleteMapping("/students/{id}")
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }

    @GetMapping("/groups")
    public List<Groups> findAllGroups(){
        return groupRepository.findAll();
    }
}