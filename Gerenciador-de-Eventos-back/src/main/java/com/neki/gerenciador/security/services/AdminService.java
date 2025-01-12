package com.neki.gerenciador.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.neki.gerenciador.dto.AdminDto;
import com.neki.gerenciador.security.entities.Admin;
import com.neki.gerenciador.security.repositories.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Método para registrar o admin
    public Admin registerAdmin(AdminDto admin) {
        if(!admin.getPassword().equals(admin.getConfirmaSenha())) {
        	throw new RuntimeException("Senhas não conferem");
        }
        Admin newAdmin = new Admin();
        newAdmin.setName(admin.getNomeAdmin());
        newAdmin.setPassword(passwordEncoder.encode(admin.getPassword()));
        newAdmin.setEmail(admin.getEmail());
        return adminRepository.save(newAdmin);
    }

    // Método de autenticação (verifique se esse método está presente)
    public String authenticate(String email, String password) {
        System.out.println(email);
        System.out.println(password);
        
    	Optional<Admin> admin = adminRepository.findByEmail(email);
    	System.out.println(admin);
    	if (admin.isPresent() && passwordEncoder.matches(password, admin.get().getPassword())) {
           
            return "Authenticated successfully"; // Substitua com o retorno adequado, como um token JWT
        }
        throw new RuntimeException("Invalid email or password");
    }
}
