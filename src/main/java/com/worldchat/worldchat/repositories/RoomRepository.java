package com.worldchat.worldchat.repositories;

import com.worldchat.worldchat.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}