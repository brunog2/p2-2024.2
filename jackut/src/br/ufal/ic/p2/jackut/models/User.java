package br.ufal.ic.p2.jackut.models;

import br.ufal.ic.p2.jackut.interfaces.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um usuário no sistema Jackut.
 */
public class User implements Serializable, Observer {
    private String login;
    private String senha;
    private String nome;
    private UserAttributes attributes;
    private List<User> friends;
    private List<User> friendsRequest;
    private List<Message> messages;
    private List<String> communities;
    private List<String> communitiesMessages;

    // Relationship attributes
    private List<String> idols;
    private List<String> crushs;
    private List<String> enemies;

    // Inverse relationship attributes
    private List<String> fans;

    /**
     * Construtor da classe User.
     *
     * @param login Nome de usuário.
     * @param senha Senha do usuário.
     * @param nome  Nome real do usuário.
     */
    public User(String login, String senha, String nome) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.attributes = new UserAttributes();
        this.friends = new ArrayList<>();
        this.friendsRequest = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.communities = new ArrayList<>();
        this.communitiesMessages = new ArrayList<>();
        this.idols = new ArrayList<>();
        this.crushs = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.fans = new ArrayList<>();
    }

    @Override
    public void update(String message) {
        this.addCommunityMessage(message);
    }

    /**
     * Obtém o login do usuário.
     *
     * @return Login do usuário.
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Obtém a senha do usuário.
     *
     * @return Senha do usuário.
     */
    public String getSenha() {
        return this.senha;
    }

    /**
     * Obtém o nome real do usuário.
     *
     * @return Nome real do usuário.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Obtém a lista de amigos do usuário.
     *
     * @return Lista de amigos.
     */
    public List<User> getFriends() {
        return this.friends;
    }

    /**
     * Obtém a lista de solicitações de amizade do usuário.
     *
     * @return Lista de solicitações de amizade.
     */
    public List<User> getFriendsRequest() {
        return this.friendsRequest;
    }

    /**
     * Obtém a lista de mensagens do usuário.
     *
     * @return Lista de mensagens.
     */
    public List<Message> getMessages() {
        return this.messages;
    }

    /**
     * Adiciona uma mensagem à lista de mensagens do usuário.
     *
     * @param message Mensagem a ser adicionada.
     */
    public void addMessage(Message message) {
        this.messages.add(message);
    }

    /**
     * Define o login do usuário.
     *
     * @param login Novo login do usuário.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Define o nome real do usuário.
     *
     * @param nome Novo nome real do usuário.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define a senha do usuário.
     *
     * @param senha Nova senha do usuário.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Adiciona uma comunidade à lista de comunidades do usuário.
     *
     * @param communityName Nome da comunidade a ser adicionada.
     */
    public void addCommunity(String communityName) {
        this.communities.add(communityName);
    }

    /**
     * Remove uma comunidade da lista de comunidades do usuário.
     *
     * @param communityName Nome da comunidade a ser removida.
     */
    public void removeCommunity(String communityName) {
        this.communities.remove(communityName);
    }

    /**
     * Obtém a lista de comunidades do usuário.
     *
     * @return Lista de comunidades.
     */
    public List<String> getCommunities() {
        return this.communities;
    }

    /**
     * Adiciona uma mensagem de comunidade à lista de mensagens de comunidades do usuário.
     *
     * @param message Mensagem a ser adicionada.
     */
    public void addCommunityMessage(String message) {
        this.communitiesMessages.add(message);
    }

    /**
     * Lê a próxima mensagem de comunidade da lista de mensagens de comunidades do usuário.
     *
     * @return Mensagem de comunidade ou null se não houver mensagens.
     */
    public String readCommunityMessage() {
        if (this.communitiesMessages.isEmpty()) {
            return null;
        }

        String message = this.communitiesMessages.get(0);

        this.communitiesMessages.remove(0);

        return message;
    }

    /**
     * Adiciona um ídolo à lista de ídolos do usuário.
     *
     * @param idol Login do ídolo a ser adicionado.
     */
    public void addIdol(String idol) {
        this.idols.add(idol);
    }

    /**
     * Adiciona um paquera à lista de paqueras do usuário.
     *
     * @param crush Login do paquera a ser adicionado.
     */
    public void addCrush(String crush) {
        this.crushs.add(crush);
    }

    /**
     * Adiciona um inimigo à lista de inimigos do usuário.
     *
     * @param enemy Login do inimigo a ser adicionado.
     */
    public void addEnemy(String enemy) {
        this.enemies.add(enemy);
    }

    /**
     * Obtém a lista de ídolos do usuário.
     *
     * @return Lista de ídolos.
     */
    public List<String> getIdols() {
        return this.idols;
    }

    /**
     * Obtém a lista de paqueras do usuário.
     *
     * @return Lista de paqueras.
     */
    public List<String> getCrushs() {
        return this.crushs;
    }

    /**
     * Obtém a lista de inimigos do usuário.
     *
     * @return Lista de inimigos.
     */
    public List<String> getEnemies() {
        return this.enemies;
    }

    /**
     * Verifica se um login está na lista de ídolos do usuário.
     *
     * @param idol Login do ídolo.
     * @return true se o login estiver na lista de ídolos, false caso contrário.
     */
    public boolean isIdol(String idol) {
        return this.idols.contains(idol);
    }

    /**
     * Verifica se um login está na lista de paqueras do usuário.
     *
     * @param crush Login do paquera.
     * @return true se o login estiver na lista de paqueras, false caso contrário.
     */
    public boolean isCrush(String crush) {
        return this.crushs.contains(crush);
    }

    /**
     * Verifica se um login está na lista de inimigos do usuário.
     *
     * @param enemy Login do inimigo.
     * @return true se o login estiver na lista de inimigos, false caso contrário.
     */
    public boolean isEnemy(String enemy) {
        return this.enemies.contains(enemy);
    }

    /**
     * Adiciona um fã à lista de fãs do usuário.
     *
     * @param fan Login do fã a ser adicionado.
     */
    public void addFan(String fan) {
        this.fans.add(fan);
    }

    /**
     * Obtém a lista de fãs do usuário.
     *
     * @return Lista de fãs.
     */
    public List<String> getFans() {
        return this.fans;
    }

    /**
     * Adiciona um atributo ao usuário.
     *
     * @param name  Nome do atributo.
     * @param value Valor do atributo.
     */
    public void addAttribute(String name, String value) {
        this.attributes.addAttribute(name, value);
    }

    /**
     * Obtém o valor de um atributo do usuário.
     *
     * @param name Nome do atributo.
     * @return Valor do atributo.
     */
    public String getAttribute(String name) {
        return this.attributes.getAttribute(name);
    }

    /**
     * Retorna a representação em string do usuário.
     *
     * @return Login do usuário.
     */
    @Override
    public String toString() {
        return this.login;
    }
}