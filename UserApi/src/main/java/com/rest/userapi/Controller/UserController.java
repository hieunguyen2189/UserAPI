package com.rest.userapi.Controller;

import com.rest.userapi.Models.user;
import com.rest.userapi.Repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    userRepository repository;

    @GetMapping("/user")
    public ResponseEntity<List<user>> getAllusers(@RequestParam(required = false) String name) {
        try {
            List<user> users = new ArrayList<user>();

            if (name == null)
                repository.findAll().forEach(users::add);
            else
                repository.findBynameContaining(name).forEach(users::add);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<user> getuserById(@PathVariable("id") int id) {
        Optional<user> Data = repository.findById(id);

        if (Data.isPresent()) {
            return new ResponseEntity<>(Data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/user")
    public ResponseEntity<user> createuser(@RequestBody user us) {
        try {
            user _user = repository
                    .save(new user(us.getName(), us.getDept(), us.getAge(),us.getDetail(),us.getImg()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/user/{id}")
    public ResponseEntity<user> updateuser(@PathVariable("id") int id, @RequestBody user us) {
        Optional<user> Data = repository.findById(id);

        if (Data.isPresent()) {
            user _user = Data.get();
            _user.setName(us.getName());
            _user.setAge(us.getAge());
            _user.setDept(us.getDept());
            _user.setDetail(us.getDetail());
            _user.setImg(us.getImg());
            return new ResponseEntity<>(repository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteuser(@PathVariable("id") int id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user")
    public ResponseEntity<HttpStatus> deleteAlluser() {
        try {
            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
