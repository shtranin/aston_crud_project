package com.example.aston_crud3.model.group;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("Pri")
public class PrivateGroup extends Group {
    @Column(name = "max_members")
    private int maxMembers;

    public PrivateGroup(String name, int maxMembers) {
        super(name);
        this.maxMembers = maxMembers;
    }

    public PrivateGroup(int id, String name, int maxMembers) {
        super(id, name);
        this.maxMembers = maxMembers;
    }

    public int getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(int maxMembers) {
        this.maxMembers = maxMembers;
    }

    public PrivateGroup() {

    }
}
