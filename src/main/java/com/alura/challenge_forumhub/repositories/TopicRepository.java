package com.alura.challenge_forumhub.repositories;

import com.alura.challenge_forumhub.entities.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, Long> {

    @Query("""
    SELECT t 
    FROM TopicEntity t 
    WHERE t.status != 'DELETADO'
    """
    )

    List<TopicEntity> findAllStatusActive();
}
