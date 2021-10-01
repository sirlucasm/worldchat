package com.worldchat.worldchat.controllers;

import java.util.*;
import java.util.stream.Collectors;

import com.worldchat.worldchat.handler.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.worldchat.worldchat.repositories.UserRepository;
import com.worldchat.worldchat.models.User;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository _userRepository;

    private List<User> removePasswordOnFetch(List<User> users) {
        return users.stream().map((user) -> {
            user.setPasswordPrivate();
            return user;
        }).collect(Collectors.toList());
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> all() {
        List<User> users = _userRepository.findAll();
        users = this.removePasswordOnFetch(users);
        return users;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity me(@PathVariable(value = "id") long id) {
        Optional<User> users = _userRepository.findById(id);
        if(users.isPresent()){
            users.get().setPasswordPrivate();
            return new ResponseEntity<User>(users.get(), HttpStatus.OK);
        }
        Error error = new Error(HttpStatus.NOT_FOUND, "O usuário não foi encontrado.");
        return new ResponseEntity<>(error, error.statusText);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody User body) {
        User user = _userRepository.save(body);
        user.setPasswordPrivate();
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Object edit(@RequestBody User userUpdate) {
        Optional<User> user = _userRepository.findById(userUpdate.getId());

        if (user.isPresent()) {
            _userRepository.save(userUpdate);
            user.get().setPasswordPrivate();
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        Error error = new Error(HttpStatus.UNAUTHORIZED, "Não foi possível atualizar sua conta");
        return new ResponseEntity<>(error, error.statusText);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody User body) {
        User user = _userRepository.findByUsernameAndPassword(body.getUsername(), body.getPassword());

        if (user != null) {
            _userRepository.deleteById(user.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        Error error = new Error(HttpStatus.UNAUTHORIZED, "Não foi possível deletar sua conta");
        return new ResponseEntity<>(error, error.statusText);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody User body) {
        User user = _userRepository.findByUsernameAndPassword(body.getUsername(), body.getPassword());
        if (user != null) {
            user.setPasswordPrivate();
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        Error error = new Error(HttpStatus.UNAUTHORIZED, "Usuário ou senha incorretos");
        return new ResponseEntity<>(error, error.statusText);
    }
}
