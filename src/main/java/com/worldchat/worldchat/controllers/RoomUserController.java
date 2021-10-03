package com.worldchat.worldchat.controllers;

import java.util.*;

import com.worldchat.worldchat.handler.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.worldchat.worldchat.repositories.RoomUserRepository;
import com.worldchat.worldchat.models.RoomUser;
import com.worldchat.worldchat.models.User;

import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/room-users")
@CrossOrigin(origins = "http://localhost:3000")
public class RoomUserController {
    @Autowired
    private RoomUserRepository _roomUserRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<RoomUser> all() {
        return _roomUserRepository.findAll();
    }

    @RequestMapping(value = "/rooms/{userId}", method = RequestMethod.GET)
    public ResponseEntity myRooms(@PathVariable(value = "userId") long userId) {
        List<RoomUser> roomUser = _roomUserRepository.findByUserId(userId);
        if(!roomUser.isEmpty()){
            return new ResponseEntity<>(roomUser, HttpStatus.OK);
        }
        Error error = new Error(HttpStatus.NOT_FOUND, "Você não está em nenhuma sala ainda.");
        return new ResponseEntity<>(error, error.statusText);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody RoomUser body) {
        RoomUser roomUser = _roomUserRepository.save(body);
        return new ResponseEntity<>(roomUser, HttpStatus.CREATED);
    }

    @Transactional
    @RequestMapping(value = "/{roomId}", method = RequestMethod.DELETE)
    public ResponseEntity exitRoom(@RequestBody User body, @PathVariable(value = "roomId") long roomId) {
        _roomUserRepository.deleteByRoomIdAndUserId(roomId, body.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
