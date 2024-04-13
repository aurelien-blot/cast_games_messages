package com.castruche.cast_games_messages.formatter;

import com.castruche.cast_games_messages.dto.UserDto;
import com.castruche.cast_games_messages.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserFormatter implements IFormatter<User, UserDto>{

    @Override
    public UserDto entityToDto(User entity) {
        if(entity == null){
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setName(entity.getName());
        return userDto;
    }

    @Override
    public User dtoToEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        return user;
    }
}
