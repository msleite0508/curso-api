package br.com.marcio.api.services.impl;

import br.com.marcio.api.domain.Users;
import br.com.marcio.api.domain.dto.UserDTO;
import br.com.marcio.api.repositories.UserRepository;
import br.com.marcio.api.services.UserService;
import br.com.marcio.api.services.exceptions.DataIntegratyViolationException;
import br.com.marcio.api.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Users> findAll() {
        return repository.findAll();
    }

    @Override
    public Users create(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, Users.class));
    }

    @Override
    public Users update(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, Users.class));
    }

    private void findByEmail(UserDTO obj){
        Optional<Users> users = repository.findByEmail(obj.getEmail());
        if(users.isPresent() && !users.get().getId().equals(obj.getId())) {
            throw new DataIntegratyViolationException("Email ja cadastrado no sistema");
        }
    }
}
