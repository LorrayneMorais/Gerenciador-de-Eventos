package com.neki.gerenciador.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.neki.gerenciador.dto.AdminDto;
import com.neki.gerenciador.dto.LoginDto;
import com.neki.gerenciador.security.entities.Admin;
import com.neki.gerenciador.security.services.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Endpoint para registrar um novo admin
    @PostMapping("/register")
    public ResponseEntity<Admin> cadastrarAdmin(@RequestBody AdminDto produto) {
        return ResponseEntity.ok(adminService.registerAdmin(produto));
    }

    // Endpoint para login do admin
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto adminDto) {
    	System.out.println(adminDto);
        return ResponseEntity.ok(adminService.authenticate(adminDto.getEmail(), adminDto.getPassword()));
    }
    
}
