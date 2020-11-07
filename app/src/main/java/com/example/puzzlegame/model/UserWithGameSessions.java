package com.example.puzzlegame.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithGameSessions {
    @Embedded
    public User userId;
    @Relation(parentColumn = "id",
    entityColumn = "user")
    List<GameSession> userGameSessions;
}
