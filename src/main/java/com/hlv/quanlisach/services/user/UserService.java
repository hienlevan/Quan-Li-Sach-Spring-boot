package com.hlv.quanlisach.services.user;

import com.hlv.quanlisach.dtos.user.UserDto;
import com.hlv.quanlisach.entities.User;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.util.List;

public interface UserService {

    Page<User> filter(String search, int page,
                      int size, String sort, String column);

    List<User> getAllUsers(String search);

    User getUser(String id);

    User getUserByEmail(String email);

    User create(UserDto userDto, Principal principal);

    User update(String id, UserDto dto, Principal principal);

    User changeStatus(String id, Principal principal);
}
