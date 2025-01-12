package com.neki.gerenciador.security.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
	public class Admin {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @Column(name="name")
	    private String name;
	    @Column(name="email")
	    private String email;
	    @Column(name="password")
	    private String password;
	    
	    @OneToMany(mappedBy="admin")
	    private List<Event> events;
	    
	    public List<Event> getEvents() {
	        return events;
	    }

	    public void setEvents(List<Event> events) {
	        events.forEach(e -> e.setAdmin(this));
	        this.events = events;
	    }
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
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
	

}
