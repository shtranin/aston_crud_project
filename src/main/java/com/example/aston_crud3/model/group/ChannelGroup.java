package com.example.aston_crud3.model.group;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Cha")
public class ChannelGroup extends Group {
    @Column(name = "author")
    private String author;

    public ChannelGroup(String name,String author) {
        super(name);
        this.author = author;
    }

    public ChannelGroup(int id, String name, String author) {
        super(id, name);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ChannelGroup() {

    }
}
