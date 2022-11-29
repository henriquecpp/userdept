package com.henrique.userdept.services;

import com.henrique.userdept.entities.User;
import com.henrique.userdept.repositories.DepartmentRepository;
import com.henrique.userdept.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class UserService {

    @Autowired
    private UserRepository repo;
    @Autowired
    private DepartmentRepository dpRepo;

    public List<User> listAll(){
        List<User> result =  repo.findAll();

        return result;
    }

    public User findById(Long id){
        if(id == null || !existsById(id)) return null;

        return repo.findById(id).get();
    }

    public User insert(User user){
        User result = repo.save(user);

        return result;
    }

    public User dataUpdate(User user){

        if(!existsById(user.getId()) || Objects.nonNull(user.getDepartment()) && !dpExistsById(user.getDepartment().getId()))
            return null;

        User result = repo.findById(user.getId()).get();

        if(user.getEmail()!=null)
            result.setEmail(user.getEmail());
        if(user.getName()!=null)
            result.setName(user.getName());
        if(user.getDepartment()!=null)
            result.setDepartment(user.getDepartment());

        repo.save(result);

        return result;
    }

    public boolean delete(Long id){
        if(id == null || !existsById(id)) return false;

        repo.deleteById(id);

        return true;
    }

    public boolean existsById(long id){
        return repo.existsById(id);
    }
    private boolean dpExistsById(long id) {
        return dpRepo.existsById(id);
    }
}
