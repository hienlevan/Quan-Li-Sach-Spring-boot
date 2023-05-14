package com.hlv.quanlisach.services.user;

import com.hlv.quanlisach.dtos.user.UserDto;
import com.hlv.quanlisach.entities.User;
import com.hlv.quanlisach.exceptions.NotFoundException;
import com.hlv.quanlisach.repositories.user.UserRepository;
import com.hlv.quanlisach.utils.PageUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> filter(String search, int page, int size, String sort, String column) {
        Pageable pageable = PageUtils.createPageable(page, size, sort,column);
        return userRepository.filter(search, pageable);
    }

    @Override
    public List<User> getAllUsers(String search) {
        return null;
    }

    @Override
    public User getUser(String id) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUser(email)
                .orElseThrow(() -> new NotFoundException(
                        String.format("User with email %s does not exist", email)
                ));
    }

    @Override
    public User create(UserDto userDto, Principal principal) {
        return null;
    }

    @Override
    public User update(String id, UserDto dto, Principal principal) {
        return null;
    }

    @Override
    public User changeStatus(String id, Principal principal) {
        return null;
    }
}
