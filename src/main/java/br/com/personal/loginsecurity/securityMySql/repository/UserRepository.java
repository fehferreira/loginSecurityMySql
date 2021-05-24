package br.com.personal.loginsecurity.securityMySql.repository;

import br.com.personal.loginsecurity.securityMySql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    User findByUserName(String userName);
}
