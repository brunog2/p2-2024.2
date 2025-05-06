package br.ufal.ic.p2.jackut.models;

import br.ufal.ic.p2.jackut.interfaces.Observer;
import br.ufal.ic.p2.jackut.interfaces.Subject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe que representa uma comunidade no sistema Jackut.
 * Implementa as interfaces Subject e Serializable.
 */
public class Community implements Serializable, Subject {
    private String name;
    private String description;
    private User owner;
    private List<String> members = new ArrayList<String>();
    private final Set<Observer> observers = new HashSet<Observer>();

    /**
     * Construtor da classe Community.
     *
     * @param name        Nome da comunidade.
     * @param description Descrição da comunidade.
     * @param owner       Usuário criador e dono da comunidade.
     */
    public Community(String name, String description, User owner) {
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.members.add(owner.getLogin());
        this.observers.add(owner);
    }

    /**
     * Registra um observador na comunidade.
     *
     * @param o Observador a ser registrado.
     */
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    /**
     * Remove um observador da comunidade.
     *
     * @param o Observador a ser removido.
     */
    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    /**
     * Notifica todos os observadores da comunidade com uma mensagem.
     *
     * @param message Mensagem a ser enviada aos observadores.
     */
    @Override
    public void notifyObservers(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }

    /**
     * Obtém o nome da comunidade.
     *
     * @return Nome da comunidade.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome da comunidade.
     *
     * @param name Novo nome da comunidade.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém a descrição da comunidade.
     *
     * @return Descrição da comunidade.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define a descrição da comunidade.
     *
     * @param description Nova descrição da comunidade.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtém o dono da comunidade.
     *
     * @return Usuário dono da comunidade.
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Define o dono da comunidade.
     *
     * @param owner Novo dono da comunidade.
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Obtém a lista de membros da comunidade.
     *
     * @return Lista de logins dos membros da comunidade.
     */
    public List<String> getMembers() {
        return this.members;
    }

    /**
     * Define a lista de membros da comunidade.
     *
     * @param members Nova lista de logins dos membros da comunidade.
     */
    public void setMembers(List<String> members) {
        this.members = members;
    }

    /**
     * Adiciona um membro à comunidade.
     *
     * @param user Usuário a ser adicionado como membro.
     */
    public void addMember(User user) {
        this.members.add(user.getLogin());
    }

    /**
     * Remove um membro da comunidade.
     *
     * @param user Usuário a ser removido da comunidade.
     */
    public void removeMember(User user) {
        this.members.remove(user.getLogin());
    }
}