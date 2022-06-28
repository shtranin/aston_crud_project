package com.example.aston_crud3.model;


import com.example.aston_crud3.model.group.Group;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected long id;
    @Column(name = "name")
    protected String name;
    @Column(name = "email")
    protected String email;
    @ManyToMany
    @JoinTable(name="users_groups",
    joinColumns=@JoinColumn(name="group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    protected List<Group> groups;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public User() {
    }

    public User(String name, String email) {
        super();
        this.name = name;
        this.email = email;

    }

    public User(long id, String name, String email) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;

    }

    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    public void subscribeAtGroup(Group group){
        groups.add(group);
    }
}
