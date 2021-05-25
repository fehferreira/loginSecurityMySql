package br.com.personal.loginsecurity.loginspringbootsecurityjpa.service;

import br.com.personal.loginsecurity.securityMySql.model.User;
import br.com.personal.loginsecurity.securityMySql.repository.RoleRepository;
import br.com.personal.loginsecurity.securityMySql.repository.UserRepository;
import br.com.personal.loginsecurity.securityMySql.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private RoleRepository mockRoleRepository;

    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private UserService userServiceUnderTest;
    private User user;

    @BeforeAll
    public void setUp(){
        initMocks(this);
        userServiceUnderTest = new UserService(mockUserRepository, mockRoleRepository, mockBCryptPasswordEncoder);

        user = User.builder()
                .id(1)
                .name("Felipe")
                .lastName("Ferreira")
                .email("test@test.com")
                .build();

        Mockito.when(mockUserRepository.save(any(User.class))).thenReturn(user);
        Mockito.when(mockUserRepository.findByEmail(anyString())).thenReturn(user);
    }


}
