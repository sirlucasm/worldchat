package com.worldchat.worldchat.controllers;

import java.util.*;

import com.worldchat.worldchat.handler.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.worldchat.worldchat.repositories.RoomMessageRepository;
import com.worldchat.worldchat.models.RoomMessage;
import com.worldchat.worldchat.models.User;

import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/room-messages")
@CrossOrigin(origins = "http://localhost:3000")
public class RoomMessageController {
    @Autowired
    private RoomMessageRepository _roomMessageRepository;

    @RequestMapping(value = "/messages/{roomId}", method = RequestMethod.GET)
    public ResponseEntity messagesByRoom(@PathVariable(value = "roomId") long roomId) {
        List<RoomMessage> roomMessages = _roomMessageRepository.findByRoomIdOrderByIdAsc(roomId);
        if(!roomMessages.isEmpty()){
            return new ResponseEntity<>(roomMessages, HttpStatus.OK);
        }
        Error error = new Error(HttpStatus.NOT_FOUND, "Esta sala não possui nenhuma mensagem ainda.");
        return new ResponseEntity<>(error, error.statusText);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody RoomMessage body) {
        RoomMessage roomMessage = _roomMessageRepository.save(body);
        return new ResponseEntity<>(roomMessage, HttpStatus.CREATED);
    }

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteMessage(@PathVariable(value = "id") long id) {
        _roomMessageRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
