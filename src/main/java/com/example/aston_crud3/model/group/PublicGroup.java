package com.example.aston_crud3.model.group;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Pub")
public class PublicGroup extends Group {
    @Column(name = "theme")
    private String theme;

    public PublicGroup(String name, String theme) {
        super(name);
        this.theme = theme;
    }

    public PublicGroup(int id, String name, String theme) {
        super(id, name);
        this.theme = theme;
    }

    public PublicGroup() {

    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
