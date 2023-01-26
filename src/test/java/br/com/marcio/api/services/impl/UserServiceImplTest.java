package br.com.marcio.api.services.impl;

import br.com.marcio.api.domain.Users;
import br.com.marcio.api.domain.dto.UserDTO;
import br.com.marcio.api.repositories.UserRepository;
import br.com.marcio.api.services.exceptions.DataIntegratyViolationException;
import br.com.marcio.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID          = 1;
    public static final String NAME         = "vladir";
    public static final String EMAIL        = "valdir@email.com";
    public static final String PASSWORD     = "123";
    public static final int INDEX = 0;
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private Users user;
    private UserDTO userDTO;
    private Optional<Users> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);

        Users response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(NAME, response.getName());
    }

    @Test
    void whenFindByIdThenReturnObjectNotFoundException(){
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));
        try {
            service.findById(ID);
        }catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
         }
        }


    @Test
    void whenFindAllThenReturnAnListOfUsers() {
          when(repository.findAll()).thenReturn(List.of(user));

        List<Users> response = service.findAll();

        assertNotNull(response); //não é nulo
        assertEquals(1, response.size()); //tamanho da lista é 1
        assertEquals(Users.class, response.get(INDEX).getClass());//objeto que vai vir dentro da lista tenha a classo
        // do tipouser

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());


    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(user)).thenReturn(user);

        Users response = service.create(userDTO);

        assertNotNull(response);
        assertNotNull(Users.class, String.valueOf(response.getClass()));
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());

    }

    @Test
    void whenCreateThenReturnDataIntegrationViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);
        try{
            optionalUser.get().setId(2);
            service.create(userDTO);
        }catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getMessage());
            assertEquals("Email ja cadastrado no sistema", ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.save(user)).thenReturn(user);

        Users response = service.update(userDTO);

        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());

    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new Users(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new Users(ID, NAME, EMAIL, PASSWORD));
    }
}