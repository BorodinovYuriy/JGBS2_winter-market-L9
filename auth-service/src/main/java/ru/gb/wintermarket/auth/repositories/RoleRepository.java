package ru.gb.wintermarket.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.wintermarket.auth.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {


}
