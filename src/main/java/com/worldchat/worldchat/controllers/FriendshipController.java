package com.worldchat.worldchat.controllers;

import java.util.*;
import java.util.stream.Collectors;

import com.worldchat.worldchat.handler.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.worldchat.worldchat.models.User;
import com.worldchat.worldchat.models.Friendship;
import com.worldchat.worldchat.repositories.FriendshipRepository;

@RestController
@RequestMapping("/friendships")
@CrossOrigin(origins = "http://localhost:3000")
public class FriendshipController {
    @Autowired
    private FriendshipRepository _friendshipRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Friendship> all() {
        return _friendshipRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity find(@PathVariable(value = "id") long id) {
        Optional<Friendship> friendship = _friendshipRepository.findById(id);
        if(friendship.isPresent()){
            return new ResponseEntity<Friendship>(friendship.get(), HttpStatus.OK);
        }
        Error error = new Error(HttpStatus.NOT_FOUND, "Solicitação de amizade não foi encontrada.");
        return new ResponseEntity<>(error, error.statusText);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Friendship body) {
        Friendship friendship = _friendshipRepository.save(body);
        return new ResponseEntity<>(friendship, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(value = "id") long id) {
        _friendshipRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public ResponseEntity friends(@RequestBody User body) {
        List<Friendship> friendships = _friendshipRepository.findBySendedByAndAccepted(body, true);
        if(!friendships.isEmpty()){
            return new ResponseEntity<>(friendships, HttpStatus.OK);
        }
        Error error = new Error(HttpStatus.NOT_FOUND, "Você não tem nenhum amigo ainda.");
        return new ResponseEntity<>(error, error.statusText);
    }
}
