package com.worldchat.worldchat.repositories;

import com.worldchat.worldchat.models.RoomMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomMessageRepository extends JpaRepository<RoomMessage, Long> {
    List<RoomMessage> findByRoomIdOrderByIdAsc(Long room_id);
}