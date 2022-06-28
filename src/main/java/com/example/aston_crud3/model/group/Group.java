package com.example.aston_crud3.model.group;

import com.example.aston_crud3.model.User;

import javax.persistence.*;
import java.util.List;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "GR_TYPE")
@Entity
@Table(name="groups_inh")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @Column(name = "name")
    protected String name;
    @ManyToMany(mappedBy = "groups")
    protected List<User> users;

    public Group(String name) {
        this.name = name;
    }

    public Group(long id, String name) {
        this.id = id;
        this.name = name;

    }

    public Group() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
