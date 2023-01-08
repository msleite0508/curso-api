package br.com.marcio.api.services;

import br.com.marcio.api.domain.Users;
import br.com.marcio.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    Users findById(Integer id);
    List<Users> findAll();
    Users create(UserDTO obj);
}
