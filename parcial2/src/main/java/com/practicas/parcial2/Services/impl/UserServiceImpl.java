package com.practicas.parcial2.Services.impl;

import com.practicas.parcial2.Entities.UserEntity;
import com.practicas.parcial2.Models.User;
import com.practicas.parcial2.Repositories.UserJpaRepository;
import com.practicas.parcial2.Services.UserService;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User getUserById(Long id) {
        try {
            UserEntity userEntity = userJpaRepository.getReferenceById(id);
            if (userEntity == null) {
                throw new NotFoundException("User not found with id: " + id);
            }
            User user = modelMapper.map(userEntity, User.class);
            return user;
        } catch (Exception e) {
            // Log the exception or handle it accordingly
            throw new ServiceException("Failed to get user with id: " + id, e);
        }
    }

    @Override
    public User saveUser(User user) {
        try {
            UserEntity userEntity = modelMapper.map(user, UserEntity.class);
            UserEntity userEntitySaved = userJpaRepository.save(userEntity);
            return modelMapper.map(userEntitySaved, User.class);
        } catch (Exception e) {
            // Log the exception or handle it accordingly
            throw new ServiceException("Failed to save user", e);
        }
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        try {
            UserEntity existingUserEntity = userJpaRepository.getReferenceById(id);
            if (existingUserEntity == null) {
                throw new NotFoundException("User not found with id: " + id);
            }

            // Actualizar los campos necesarios
            existingUserEntity.setName(updatedUser.getName());
            existingUserEntity.setLastName(updatedUser.getLastName());
            existingUserEntity.setUserName(updatedUser.getUserName());
            existingUserEntity.setPassword(updatedUser.getPassword());

            UserEntity updatedUserEntity = userJpaRepository.save(existingUserEntity);
            return modelMapper.map(updatedUserEntity, User.class);
        } catch (Exception e) {
            // Log the exception or handle it accordingly
            throw new ServiceException("Failed to update user with id: " + id, e);
        }
    }
}
