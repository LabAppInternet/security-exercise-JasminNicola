package cat.tecnocampus.securityjwt.persistence;

import cat.tecnocampus.securityjwt.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
