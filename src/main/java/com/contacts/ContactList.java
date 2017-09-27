package com.contacts;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by maja.grabczynska on 2017-09-26.
 */

@UIScope
@SpringComponent
public class ContactList extends VerticalLayout {

    @Autowired
    private ContactRepository repository;
    private List<Contact> contacts;

    @PostConstruct
    void init() {
        update();
    }

    private void update() {
        setContacts(repository.findAll());
    }

    private void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        removeAllComponents();
        contacts.forEach(contact -> {
            addComponent(new ContactLayout(contact));
        });
    }

    void add(Contact contact) {
        repository.save(contact);
        update();
    }

    public void deleteCompleted() {
        repository.deleteByDone(true);
        update();
    }
}
