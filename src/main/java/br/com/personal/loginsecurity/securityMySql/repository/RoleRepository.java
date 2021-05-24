package br.com.personal.loginsecurity.securityMySql.repository;

import br.com.personal.loginsecurity.securityMySql.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
