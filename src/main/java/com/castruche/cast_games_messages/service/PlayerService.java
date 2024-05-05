package com.castruche.cast_games_messages.service;

import com.castruche.cast_games_messages.dao.PlayerRepository;
import com.castruche.cast_games_messages.dao.PrivateConversationRepository;
import com.castruche.cast_games_messages.dto.MessageReceptionDto;
import com.castruche.cast_games_messages.dto.PlayerDto;
import com.castruche.cast_games_messages.dto.PlayerLightDto;
import com.castruche.cast_games_messages.dto.PrivateConversationDto;
import com.castruche.cast_games_messages.entity.Player;
import com.castruche.cast_games_messages.entity.PrivateConversation;
import com.castruche.cast_games_messages.formatter.PlayerFormatter;
import com.castruche.cast_games_messages.formatter.PrivateConversationFormatter;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PlayerService extends GenericService<Player, PlayerDto>{

    private static final Logger logger = LogManager.getLogger(PlayerService.class);

    private PlayerRepository playerRepository;
    private PlayerFormatter playerFormatter;


    public PlayerService(PlayerRepository playerRepository, PlayerFormatter playerFormatter){
        super(playerRepository, playerFormatter);
        this.playerRepository = playerRepository;
        this.playerFormatter = playerFormatter;
    }

    public List<Player> selectBySourcePlayerIdIn(List<Long> ids){
        return this.playerRepository.findBySourcePlayerIdIn(ids);
    }

    @Transactional
    public Player selectBySourcePlayerId(Long sourcePlayerId){
        return this.playerRepository.findBySourcePlayerId(sourcePlayerId);
    }

    @Transactional
    public void createIfPlayersNotExist(List<PlayerLightDto> players){
        if(players == null || players.isEmpty()) {
            throw new IllegalArgumentException("Players are required");
        }
        //On formate la liste pour éviter les doublons
        HashMap<Long, PlayerLightDto> map = new HashMap<>();
        for(PlayerLightDto player : players){
            map.put(player.getId(), player);
        }
        players = map.values().stream().toList();

        List<Long> sourcePlayerIds = players.stream().map(PlayerLightDto::getId).toList();
        List<Player> existingPlayers = this.selectBySourcePlayerIdIn(sourcePlayerIds);
        List<PlayerLightDto> newPlayers = players.stream().filter(player -> existingPlayers.stream().noneMatch(existingPlayer -> existingPlayer.getSourcePlayerId().equals(player.getId()))).toList();
        if(newPlayers.isEmpty()){
            return;
        }
        List<Player> playersToSave = playerFormatter.lightDtoToEntity(newPlayers);
        this.playerRepository.saveAll(playersToSave);
    }

}
