package com.example.puzzlegame.model.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.puzzlegame.model.GameSession;
import com.example.puzzlegame.model.User;

import java.util.List;

public class UserWithGameSessions {
    @Embedded
    public User userId;
    @Relation(parentColumn = "currentGameId",
    entityColumn = "userId")
    public List<GameSession> userGameSessions;
}
