package com.worldchat.worldchat.models;

import javax.persistence.*;

@Entity
@Table(name = "friendships")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sended_by")
    private User sendedBy;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "to_user_id")
    private User toUser;
    @Column(name = "accepted", nullable = false)
    private Boolean accepted = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSendedBy() {
        return sendedBy;
    }

    public void setSendedBy(User sendedBy) {
        this.sendedBy = sendedBy;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}