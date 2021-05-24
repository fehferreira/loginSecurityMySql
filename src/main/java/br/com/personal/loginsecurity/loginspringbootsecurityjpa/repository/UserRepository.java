package br.com.personal.loginsecurity.loginspringbootsecurityjpa.repository;

import br.com.personal.loginsecurity.loginspringbootsecurityjpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    User findByUserName(String userName);
}
