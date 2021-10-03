package com.worldchat.worldchat.controllers;

import java.util.*;
import java.util.stream.Collectors;

import com.worldchat.worldchat.handler.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.worldchat.worldchat.repositories.RoomRepository;
import com.worldchat.worldchat.models.Room;

@RestController
@RequestMapping("/rooms")
@CrossOrigin(origins = "http://localhost:3000")
public class RoomController {
    @Autowired
    private RoomRepository _roomRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Room> all() {
        return _roomRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity find(@PathVariable(value = "id") long id) {
        Optional<Room> room = _roomRepository.findById(id);
        if(room.isPresent()){
            return new ResponseEntity<Room>(room.get(), HttpStatus.OK);
        }
        Error error = new Error(HttpStatus.NOT_FOUND, "Não foi possível encontrar a sala.");
        return new ResponseEntity<>(error, error.statusText);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Room body) {
        Room room = _roomRepository.save(body);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(value = "id") long id) {
        _roomRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity changeRoomName(@RequestBody Room body, @PathVariable(value = "id") long id) {
        Optional<Room> oldRoom = _roomRepository.findById(id);
        if (oldRoom.isPresent()) {
            Room room = oldRoom.get();
            room.setName(body.getName());
            _roomRepository.save(room);
            return new ResponseEntity<>(room, HttpStatus.OK);
        }
        Error error = new Error(HttpStatus.NOT_FOUND, "Não foi possível alterar o nome da sala.");
        return new ResponseEntity<>(error, error.statusText);
    }
}
