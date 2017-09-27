package com.contacts;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by maja.grabczynska on 2017-09-26.
 */

@SpringUI
@Theme("valo")
public class ContactsUI extends UI{

    private VerticalLayout layout;

    @Autowired
    ContactList contactList;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        addHeader();
        addForm();
        addContactList();
        addActionButton();
    }

    private void setupLayout() {
        layout = new VerticalLayout();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(layout);
    }

    private void addHeader() {
        Label header = new Label("Address Book");
        header.addStyleName(ValoTheme.LABEL_H1);
        header.setSizeUndefined();
        layout.addComponent(header);
    }

    private void addForm() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        TextField firstName = new TextField();
        firstName.setWidth("80%");
        firstName.setCaption("First Name");
        firstName.setDescription("First Name");

        TextField lastName = new TextField();
        lastName.setWidth("80%");
        lastName.setCaption("Last Name");
        lastName.setDescription("Last Name");

        Button addButton = new Button("Add new contact");
        addButton.setIcon(FontAwesome.USER_PLUS);
        addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);

        formLayout.setWidth("100%");
        formLayout.addComponents(firstName, lastName, addButton);

        addButton.addClickListener(click -> {
            contactList.add(new Contact(firstName.getValue(), lastName.getValue()));
            firstName.clear();
            lastName.clear();
            firstName.focus();
        });
        firstName.focus();
        addButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        layout.addComponent(formLayout);
    }

    private void addContactList() {
        contactList.setWidth("80%");
        layout.addComponent(contactList);
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
    }

    private void addActionButton() {
        Button deleteButton = new Button("Delete", click -> {
            contactList.deleteCompleted();
        });
        deleteButton.setIcon(FontAwesome.TRASH);
        deleteButton.addStyleName(ValoTheme.BUTTON_DANGER);
        layout.addComponent(deleteButton);
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
    }
}
