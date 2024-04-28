package com.castruche.cast_games_messages.dao;

import com.castruche.cast_games_messages.entity.Message;
import com.castruche.cast_games_messages.entity.Player;
import com.castruche.cast_games_messages.entity.PrivateConversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivateConversationRepository extends JpaRepository<PrivateConversation, Long> {

    List<PrivateConversation> findByPlayer1IdOrPlayer2Id(Long player1_id, Long player2_id);
}
