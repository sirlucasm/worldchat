package com.worldchat.worldchat.repositories;

import com.worldchat.worldchat.models.Friendship;
import com.worldchat.worldchat.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findBySendedByAndAccepted(User sendedBy, Boolean accepted);
}