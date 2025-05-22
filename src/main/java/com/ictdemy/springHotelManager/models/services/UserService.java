package com.ictdemy.springHotelManager.models.services;


import com.ictdemy.springHotelManager.data.entities.UserEntity;
import com.ictdemy.springHotelManager.models.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    void create(UserDTO user);

    List<UserDTO> getall();

    void remove(String username);

    Optional<UserEntity> findByEmail(String email);

    void update(UserDTO user);

    Optional<UserDTO> findById(long userId);

    UserDetails loadUserByUsername(String username);
}
