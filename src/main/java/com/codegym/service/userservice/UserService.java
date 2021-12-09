package com.codegym.service.userservice;

import com.codegym.model.AppUser;
import com.codegym.repository.IAppRoleRepo;
import com.codegym.repository.IAppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService implements IAppUserService, UserDetailsService {

    @Autowired
    private IAppUserRepo userRepo;


    @Override
    public AppUser getUserByUsername(String name) {
        return userRepo.findByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = getUserByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(user.getRole());
        String name = user.getName();
        UserDetails userDetails = new User(
                name,
                user.getPassword(),
                authorities
        );
        return userDetails;
    }
}
