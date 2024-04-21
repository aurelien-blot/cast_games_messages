package com.castruche.cast_games_messages.formatter;

import com.castruche.cast_games_messages.dto.PlayerDto;
import com.castruche.cast_games_messages.dto.PlayerLightDto;
import com.castruche.cast_games_messages.entity.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerFormatter implements IFormatter<Player, PlayerDto>{

    @Override
    public PlayerDto entityToDto(Player entity) {
        if(entity == null){
            return null;
        }
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(entity.getId());
        playerDto.setUsername(entity.getUsername());
        return playerDto;
    }

    @Override
    public Player dtoToEntity(PlayerDto dto) {
        Player player = new Player();
        player.setId(dto.getId());
        player.setUsername(dto.getUsername());
        return player;
    }

    public Player lightDtoToEntity(PlayerLightDto dto) {
        Player player = new Player();
        player.setSourcePlayerId(dto.getId());
        player.setUsername(dto.getUsername());
        return player;
    }

    public List<Player> lightDtoToEntity(List<PlayerLightDto> dto) {
        List<Player> entityList = new ArrayList<>();
        if(null != dto){
            for(PlayerLightDto playerLightDto : dto){
                entityList.add(lightDtoToEntity(playerLightDto));
            }
        }
        return entityList;
    }
}
