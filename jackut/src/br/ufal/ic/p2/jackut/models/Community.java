package br.ufal.ic.p2.jackut.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Community implements Serializable {
    private String name;
    private String description;
    private User owner;
    private List<String> members = new ArrayList<String>();

    public Community(String name, String description, User owner) {
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.members.add(owner.getLogin());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public void addMember(User user) {
        this.members.add(user.getLogin());
    }

    public void removeMember(User user) {
        this.members.remove(user.getLogin());
    }
}
