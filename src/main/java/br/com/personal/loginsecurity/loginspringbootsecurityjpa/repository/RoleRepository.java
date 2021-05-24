package br.com.personal.loginsecurity.loginspringbootsecurityjpa.repository;

import br.com.personal.loginsecurity.loginspringbootsecurityjpa.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
