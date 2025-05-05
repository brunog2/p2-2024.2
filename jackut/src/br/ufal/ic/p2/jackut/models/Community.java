package br.ufal.ic.p2.jackut.models;

import br.ufal.ic.p2.jackut.interfaces.Observer;
import br.ufal.ic.p2.jackut.interfaces.Subject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Community implements Serializable, Subject {
    private String name;
    private String description;
    private User owner;
    private List<String> members = new ArrayList<String>();
    private final Set<Observer> observers = new HashSet<Observer>();

    public Community(String name, String description, User owner) {
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.members.add(owner.getLogin());
        this.observers.add(owner);
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
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

    public String getMembers() {
        return "{" + String.join(",", this.members) + "}";
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
