package com.worldchat.worldchat.models;

import javax.persistence.*;
import java.util.Optional;

@Table(name = "rooms")
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 80)
    private String name;
    @Column(name = "room_pic", nullable = true, length = 999)
    private String roomPic;
    @Column(name = "max_users", nullable = false)
    private Integer maxUsers;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomPic() {
        return roomPic;
    }

    public void setRoomPic(String roomPic) {
        this.roomPic = roomPic;
    }

    public Integer getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(Integer maxUsers) {
        this.maxUsers = maxUsers;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}