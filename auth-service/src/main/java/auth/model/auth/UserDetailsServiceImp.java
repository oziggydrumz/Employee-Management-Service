package auth.model.auth;



import exception.EmployeeException;
import lombok.RequiredArgsConstructor;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repo.UserRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {


    private  final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> users = userRepo.findByEmail(email);


        if (users.isEmpty()) {
            try {
                throw new EmployeeException("unKnow user", HttpStatus.NOT_FOUND);
            } catch (EmployeeException e) {
                throw new RuntimeException(e);
            }



        }
        User user =users.get();


        List<GrantedAuthority> authorities = user.getAppRoles().stream()
                .map(myRoles -> new SimpleGrantedAuthority("ROLE_".concat(myRoles.getTitle())))
                .collect(Collectors.toList());


        return new auth.model.auth.UserDetailsImpl(user.getId(),
                user.getPassWord(),
                authorities,
                user.getEmail());

    }
}
