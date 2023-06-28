package com.practicas.parcial2.Services;

import com.practicas.parcial2.Entities.UserEntity;
import com.practicas.parcial2.Models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUserById(Long id);

    User saveUser(User user);

    User updateUser(Long id, User updatedUser);
}
