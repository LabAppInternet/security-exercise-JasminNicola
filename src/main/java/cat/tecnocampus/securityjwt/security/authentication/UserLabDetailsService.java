package cat.tecnocampus.securityjwt.security.authentication;

import cat.tecnocampus.securityjwt.domain.Role;
import cat.tecnocampus.securityjwt.domain.UserLab;
import cat.tecnocampus.securityjwt.persistence.RoleRepository;
import cat.tecnocampus.securityjwt.persistence.UserLabRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLabDetailsService implements UserDetailsService {
    private final RoleRepository roleRepository;
    private UserLabRepository userLabRepository;

    public UserLabDetailsService(UserLabRepository userLabRepository, RoleRepository roleRepository) {
        this.userLabRepository = userLabRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLab user = userLabRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return new UserLabDetails(user);
    }

    public UserLab saveUserLab(UserLab userLab) {
        return userLabRepository.save(userLab);
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    public List<UserLab> getAllUsers() {
        return userLabRepository.findAll();
    }
}