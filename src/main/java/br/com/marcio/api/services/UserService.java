package br.com.marcio.api.services;

import br.com.marcio.api.domain.User;

public interface UserService {
    User findById(Integer id);
}
