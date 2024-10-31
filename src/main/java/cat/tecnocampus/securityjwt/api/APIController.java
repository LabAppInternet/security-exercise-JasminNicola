package cat.tecnocampus.securityjwt.api;

import cat.tecnocampus.securityjwt.domain.NewUserDto;
import cat.tecnocampus.securityjwt.domain.Role;
import cat.tecnocampus.securityjwt.domain.UserLab;
import cat.tecnocampus.securityjwt.security.authentication.UserLabDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
public class APIController {

    private final UserLabDetailsService userLabDetailsService;

    public APIController(UserLabDetailsService userLabDetailsService) {
        this.userLabDetailsService = userLabDetailsService;
    }

    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/helloAdmin")
    public String helloAdmin() {
        return "Hello Admin";
    }

    @GetMapping("/helloUser")
    public String helloUser() {
        return "Hello User";
    }

    @GetMapping("/helloUserAdmin")
    public String helloUserAdmin() {
        return "Hello User or Admin";
    }

    @GetMapping("/helloMe")
    public String helloMe(Principal principal) {
        return "Hello " + principal.getName();
    }

    // getMapping /moderator returning the url
    @GetMapping("/moderator")
    public String moderator() {
        return "moderator";
    }

    @GetMapping("/moderator/aaa")
    public String moderatorAaa() {
        return "moderator/aaa";
    }

    @GetMapping("/moderator/bbb")
    public String moderatorBbb() {
        return "moderator/bbb";
    }

    @GetMapping("/moderator/ccc")
    public String moderatorCcc() {
        return "moderator/ccc";
    }

    @GetMapping("/moderator/aaa/admin")
    public String moderatorAaaAdmin() {
        return "moderator/aaa/admin";
    }

    @GetMapping("/moderator/bbb/admin")
    public String moderatorBbbAdmin() {
        return "moderator/bbb/admin";
    }

    @GetMapping("/moderator/ccc/admin")
    public String moderatorCccAdmin() {
        return "moderator/ccc/admin";
    }

    // TODO 2 add a PostMapping to create a new user with a single role. The role must be ADMIN or USER or MODERATOR
    @PostMapping("/users")
    public String addUser(@RequestBody @Validated NewUserDto user) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);//todo???
        UserLab userLab = new UserLab(user.getUsername(),user.getEmail(),passwordEncoder.encode(user.getPassword()));
        Set<Role> set =userLab.getRoles();
        set.add(user.getRole());
        userLab.setRoles(set);
        userLabDetailsService.saveRole(user.getRole());
        userLabDetailsService.saveUserLab(userLab);

        return userLab.getUsername()+ "is created" + userLab.getRoles().stream().map(role -> role.getName());
    }

   @GetMapping("/users")
    public List<UserLab> getUserLab() {
       return userLabDetailsService.getAllUsers();
   }

}
