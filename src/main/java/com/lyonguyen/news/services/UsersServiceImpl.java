package com.lyonguyen.news.services;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyonguyen.news.bean.NoOpPasswordEncoder;
import com.lyonguyen.news.models.Role;
import com.lyonguyen.news.models.User;
import com.lyonguyen.news.repositories.RoleRepository;
import com.lyonguyen.news.repositories.UsersRepository;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//    @Override
//    public void save(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setRoles(new HashSet<>(roleRepository.findAll()));
//        userRepository.save(user);
//    }
    
    @Autowired
    private NoOpPasswordEncoder noEncoder;
    
    @Override
    public boolean isPasswordMatch(String password, String confirmPassword) {
    	if(password.equals(confirmPassword)) {
    		return true;
    	}
    	return false;
    }

    @Override
    public void save(User user) {
        user.setPassword(noEncoder.encode(user.getPassword()));
        

        Role userRole = roleRepository.findByName("User");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        
        userRepository.save(user);

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
