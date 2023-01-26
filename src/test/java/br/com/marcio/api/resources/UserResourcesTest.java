package br.com.marcio.api.resources;

import br.com.marcio.api.domain.Users;
import br.com.marcio.api.domain.dto.UserDTO;
import br.com.marcio.api.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserResourcesTest {

    public static final Integer ID          = 1;
    public static final String NAME         = "vladir";
    public static final String EMAIL        = "valdir@email.com";
    public static final String PASSWORD     = "123";
    public static final int INDEX = 0;
    public static final String EMAIL_JA_CADASTRADO_NO_SISTEMA = "Email ja cadastrado no sistema";

    private Users user;

    private UserDTO userDTO;

    @InjectMocks
    private UserResources resource;

    @Mock
    private UserServiceImpl service;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new Users(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }
}