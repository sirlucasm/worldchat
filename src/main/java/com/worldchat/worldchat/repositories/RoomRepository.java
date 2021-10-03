package com.worldchat.worldchat.repositories;

import com.worldchat.worldchat.models.Room;
import com.worldchat.worldchat.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByCreatedBy(User created_by);
}