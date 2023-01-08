package br.com.marcio.api.services;

import br.com.marcio.api.domain.Users;

public interface UserService {
    Users findById(Integer id);
}
