package com.henrique.userdept.controllers;

import com.henrique.userdept.entities.Department;
import com.henrique.userdept.entities.User;
import com.henrique.userdept.repositories.DepartmentRepository;
import com.henrique.userdept.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

    @Autowired
    private UserRepository repo;
    @Autowired
    private DepartmentRepository dpRepo;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> result =  repo.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        if(id == null || !existsById(id)) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        User result =  repo.findById(id).get();

        return ResponseEntity.status(HttpStatus.FOUND).body(result);
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User user){
        User result = repo.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<User> update(@RequestBody User user){

        if(!existsById(user.getId()) || !dpExistsById(user.getDepartment().getId()))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        User result = repo.findById(user.getId()).get();
        result.setEmail(user.getEmail());
        result.setName(user.getName());
        result.setDepartment(user.getDepartment());

        repo.save(result);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id){
        if(id == null || !existsById(id)) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        repo.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public boolean existsById(long id){
        return repo.existsById(id);
    }
    private boolean dpExistsById(long id) {
        return dpRepo.existsById(id);
    }
}
