package com.ictdemy.springHotelManager.models.services;

import com.ictdemy.springHotelManager.data.entities.CustomerEntity;
import com.ictdemy.springHotelManager.data.entities.UserEntity;
import com.ictdemy.springHotelManager.data.repositories.UserRepository;
import com.ictdemy.springHotelManager.models.dto.UserDTO;
import com.ictdemy.springHotelManager.models.dto.mappers.UserMapper;
import com.ictdemy.springHotelManager.models.exceptions.DuplicateEmailException;
import com.ictdemy.springHotelManager.models.exceptions.PasswordsDoNotEqualException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void create(UserDTO user) {
        if (!user.getPassword().equals(user.getConfirmPassword())){
            throw new PasswordsDoNotEqualException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity = userMapper.toEntity(user);


        try {
            userRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException();
        }
    }

    @Override
    public List<UserDTO> getall() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public void remove(String username) {
        Optional<UserEntity> fetchedUser = findByEmail(username);
        fetchedUser.ifPresent(f -> userRepository.deleteById(fetchedUser.get().getUserId()));
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);

    }

    @Override
    public void update(UserDTO user) {
        Optional<UserEntity> fetchedUser = userRepository.findById(user.getUserId());

        if (fetchedUser.isPresent()) {
            UserEntity userEntity = fetchedUser.get();
            userMapper.updateEntityFromDTO(user, userEntity);

            if (user.getPassword() != null && !user.getPassword().isBlank()) {
                if (!user.getPassword().equals(user.getConfirmPassword())) {
                    throw new PasswordsDoNotEqualException();
                }

                String encoded = passwordEncoder.encode(user.getPassword());
                userEntity.setPassword(encoded);
            }

            try {
                userRepository.save(userEntity);
            } catch (DataIntegrityViolationException e) {
                throw new DuplicateEmailException();
            }


        } else {
            throw new EntityNotFoundException("User with ID " + user.getUserId() + " not found.");
        }
    }

    @Override
    public Optional<UserDTO> findById(long userId) {
        return userRepository.findById(userId).map(userMapper::toDto);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
