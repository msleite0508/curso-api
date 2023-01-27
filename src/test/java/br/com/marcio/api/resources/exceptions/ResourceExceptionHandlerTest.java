package br.com.marcio.api.resources.exceptions;

import br.com.marcio.api.services.exceptions.DataIntegratyViolationException;
import br.com.marcio.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {

    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    public static final String EMAIL_JA_CADASTRADADO = "Email ja cadastradado";
    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFuncExceptionReturnResponseEntity() {
        ResponseEntity<StandarError> response = exceptionHandler
                .objectNotFunc
                        (new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO),
                                new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(OBJETO_NAO_ENCONTRADO, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
        assertNotEquals("/user/2", response.getBody().getPath());
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());


    }

    @Test
    void dataIntegrityViolationException() {
        ResponseEntity<StandarError> response = exceptionHandler
                .dataIntegrityViolationException
                        (new DataIntegratyViolationException(EMAIL_JA_CADASTRADADO),
                                new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(EMAIL_JA_CADASTRADADO, response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());

    }
}