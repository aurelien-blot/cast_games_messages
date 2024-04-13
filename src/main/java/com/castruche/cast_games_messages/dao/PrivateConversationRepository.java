package com.castruche.cast_games_messages.dao;

import com.castruche.cast_games_messages.entity.Message;
import com.castruche.cast_games_messages.entity.PrivateConversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateConversationRepository extends JpaRepository<PrivateConversation, Long> {

}
