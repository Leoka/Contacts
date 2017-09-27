package com.contacts;

import com.vaadin.data.*;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by maja.grabczynska on 2017-09-26.
 */

public class ContactLayout extends HorizontalLayout {

    private final CheckBox done;
    private final TextField firstName;
    private final TextField lastName;

    public ContactLayout(Contact contact) {
        setSpacing(true);
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        done = new CheckBox();

        firstName = new TextField();
        firstName.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        firstName.setValueChangeMode(ValueChangeMode.BLUR);

        lastName = new TextField();
        lastName.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        lastName.setValueChangeMode(ValueChangeMode.BLUR);

        addComponents(done, firstName, lastName);
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        Binder<Contact> binder = new Binder<>(Contact.class);
        binder.bindInstanceFields(this);
        binder.setBean(contact);
    }
}
