package com.softuni.gameshop.service.impl;


import com.softuni.gameshop.model.DTO.UserRegisterDTO;
import com.softuni.gameshop.model.ShoppingCart;
import com.softuni.gameshop.model.UserEntity;
import com.softuni.gameshop.model.UserRole;
import com.softuni.gameshop.model.enums.UserRoleEnum;
import com.softuni.gameshop.repository.UserRepository;
import com.softuni.gameshop.repository.UserRoleRepository;
import com.softuni.gameshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public boolean register(UserRegisterDTO userRegisterDTO) {
        if (userRegisterDTO == null || !userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())){
            return false;
        }

        String username = userRegisterDTO.getUsername();
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(username);

        if (byUsername.isPresent()){
            return false;
        }

        UserEntity user = modelMapper.map(userRegisterDTO, UserEntity.class);
        UserRole userRole = userRoleRepository.findByRoleName(UserRoleEnum.USER);
        user.setUserRoles(Collections.singletonList(userRole));
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        this.userRepository.save(user);

        return true;
    }

    @Override
    public void addAdmin(){
        UserEntity user = new UserEntity();
        user.setUsername("admin");
        user.setEmail("admin@example.com");
        user.setFullName("Admin adminov");

        // Check if the "ADMIN" role exists
        UserRole userRole = userRoleRepository.findByRoleName(UserRoleEnum.ADMIN);

        // If the role doesn't exist, create and save it
        if (userRole == null) {
            userRole = new UserRole();
            userRole.setRoleName(UserRoleEnum.ADMIN);
            userRoleRepository.save(userRole);
        }

        user.setUserRoles(Collections.singletonList(userRole));
        user.setPassword(passwordEncoder.encode("topsecret"));
        userRepository.save(user);
    }


    public Optional<UserEntity> getByUsername(String username){
        return this.userRepository.findByUsername(username);
    }


}
