package org.example.chat.repository;

import org.example.chat.entity.ChatMessage;
import org.example.chat.entity.ChatParticipation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatParticipationRepository extends JpaRepository<ChatParticipation, Long> {
}
