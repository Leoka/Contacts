package com.contacts;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by maja.grabczynska on 2017-09-26.
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Transactional
    void deleteByDone(boolean done);
}
