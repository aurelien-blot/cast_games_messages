package com.castruche.cast_games_messages.dao;

import com.castruche.cast_games_messages.entity.Conversation;
import com.castruche.cast_games_messages.entity.PrivateConversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {


}
