package br.com.personal.loginsecurity.loginspringbootsecurityjpa.service;

import br.com.personal.loginsecurity.securityMySql.model.User;
import br.com.personal.loginsecurity.securityMySql.repository.RoleRepository;
import br.com.personal.loginsecurity.securityMySql.repository.UserRepository;
import br.com.personal.loginsecurity.securityMySql.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private RoleRepository mockRoleRepository;

    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private UserService userServiceUnderTest;
    private User user;

    @BeforeEach
    public void setUp(){
        initMocks(this);
        userServiceUnderTest = new UserService(mockUserRepository, mockRoleRepository, mockBCryptPasswordEncoder);

        user = User.builder()
                .id(1)
                .name("Felipe")
                .lastName("Ferreira")
                .email("test@test.com")
                .userName("felipe.ferreira")
                .build();

        Mockito.when(mockUserRepository.save(any(User.class))).thenReturn(user);
    }

    @Test
    public void testFindUserByEmail(){
        final String email = "test@test.com";

        Mockito.when(mockUserRepository.findByEmail(anyString())).thenReturn(user);

        final User result = userServiceUnderTest.findByEmail(email);

        assertEquals(email, result.getEmail());
    }

    @Test
    public void testSaveUser(){
        final String email = "test@test.com";

        User result = userServiceUnderTest.saveUser(User.builder().build());

        assertEquals(email,result.getEmail());
    }

    @Test
    public void testFindUserByUserName(){
        final String userName = "felipe.ferreira";
        final String wrongUserName = "notFelipe.ferreira";

        Mockito.when(userServiceUnderTest.findByUserName(userName)).thenReturn(user);
        Mockito.when(userServiceUnderTest.findByUserName(wrongUserName)).thenReturn(null);

        User result = userServiceUnderTest.findByUserName(userName);

        assertEquals(userName,result.getUserName());
        assertNotEquals(wrongUserName, result.getUserName());
    }

}
