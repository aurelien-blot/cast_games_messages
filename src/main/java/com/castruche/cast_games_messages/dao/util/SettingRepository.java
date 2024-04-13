package com.castruche.cast_games_messages.dao.util;


import com.castruche.cast_games_messages.entity.util.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {

    Setting findByShortName(String shortName);
}
