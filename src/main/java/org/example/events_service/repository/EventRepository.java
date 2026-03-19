package org.example.events_service.repository;

import org.example.events_service.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {


    public Event getById(UUID id);

    Page<Event> findByOrganizerUserId(UUID organizerUserId, Pageable pageable);
}
