package com.neki.gerenciador.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.neki.gerenciador.security.entities.Admin;
import com.neki.gerenciador.security.repositories.AdminRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca o usuário pelo e-mail
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found with email: " + email));

        // Retorna um objeto UserDetails com os dados do usuário
        return org.springframework.security.core.userdetails.User
                .withUsername(admin.getEmail())
                .password(admin.getPassword())
                .authorities("ROLE_ADMIN") // Permissão padrão
                .build();
    }
}
