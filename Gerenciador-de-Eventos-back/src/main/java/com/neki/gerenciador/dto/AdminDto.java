package com.neki.gerenciador.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class AdminDto {

    private String nomeAdmin;
    private String email;
    private String password;
    private String confirmaSenha;

    public AdminDto(String nomeAdmin, String email, String password, String confirmaSenha) {
        this.nomeAdmin = nomeAdmin;
        this.email = email;
        this.password = password;
        this.confirmaSenha = confirmaSenha;
    }

    public String getNomeAdmin() {
        return nomeAdmin;
    }

    public void setNomeAdmin(String nomeAdmin) {
        this.nomeAdmin = nomeAdmin;
    }

    public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public boolean isSuperAdmin() {
        return "SUPER_ADMIN".equalsIgnoreCase(password);
    }
}
