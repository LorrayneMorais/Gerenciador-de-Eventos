package com.neki.gerenciador.security.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neki.gerenciador.security.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByAdminId(Long adminId);
}