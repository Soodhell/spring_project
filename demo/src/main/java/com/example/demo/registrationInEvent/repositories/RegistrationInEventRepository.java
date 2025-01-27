package com.example.demo.registrationInEvent.repositories;

import com.example.demo.registrationInEvent.models.RegistrationInEventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RegistrationInEventRepository extends JpaRepository<RegistrationInEventModel, Long> {
    @Query(value = "select registration_in_event.id, registration_in_event.user_id, registration_in_event.event_id from registration_in_event " +
                   "inner join users On registration_in_event.user_id = users.id " +
                   "where users.mail = ?1", nativeQuery = true)
    List<RegistrationInEventModel> findAllByUserMail(String mail);

    @Modifying
    @Transactional
    @Query(value = "delete from registration_in_event " +
                   "where registration_in_event.user_id = " +
                   "(select id from users where users.mail = :mail) and" +
                   " registration_in_event.event_id = :id", nativeQuery = true)
    void deleteAllByNews_id(@Param("mail") String mail, @Param("id") Long id);
}
