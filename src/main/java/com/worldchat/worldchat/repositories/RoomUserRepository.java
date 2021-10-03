package com.worldchat.worldchat.repositories;

import com.worldchat.worldchat.models.RoomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface RoomUserRepository extends JpaRepository<RoomUser, Long> {
    List<RoomUser> findByUserId(Long user_id);
    void deleteByRoomIdAndUserId(Long room_id, Long user_id);
}