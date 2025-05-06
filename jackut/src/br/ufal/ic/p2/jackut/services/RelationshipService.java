package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.models.Message;
import br.ufal.ic.p2.jackut.models.User;
import br.ufal.ic.p2.jackut.utils.ListFormatter;

/**
 * Serviço responsável por gerenciar os relacionamentos entre usuários no sistema Jackut.
 */
public class RelationshipService {
    private final UserService userService;

    /**
     * Construtor da classe RelationshipService.
     *
     * @param userService Serviço de usuários utilizado para buscar informações dos usuários.
     */
    public RelationshipService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Adiciona um ídolo para o usuário.
     *
     * @param userLogin Login do usuário.
     * @param idolLogin Login do ídolo.
     * @throws UserCannotBeFanOfHimselfException Se o usuário tentar adicionar a si mesmo como ídolo.
     * @throws UserAlreadyAddedAsIdolException Se o ídolo já tiver sido adicionado.
     * @throws UserNotExistsException Se o usuário ou o ídolo não existirem.
     * @throws EnemyUserException Se o ídolo for um inimigo do usuário.
     */
    public void addIdol(String userLogin, String idolLogin) throws UserCannotBeFanOfHimselfException, UserAlreadyAddedAsIdolException, UserNotExistsException, EnemyUserException {
        User user = userService.getUser(userLogin);
        User idol = userService.getUser(idolLogin);

        if (user == null || idol == null) {
            throw new UserNotExistsException();
        }

        if (userLogin.equals(idolLogin)) {
            throw new UserCannotBeFanOfHimselfException();
        }

        if (isEnemy(idolLogin, userLogin)) {
            throw new EnemyUserException(idol.getNome());
        }

        if (user.isIdol(idol.getLogin())) {
            throw new UserAlreadyAddedAsIdolException();
        }

        user.addIdol(idol.getLogin());
        idol.addFan(user.getLogin());
    }

    /**
     * Verifica se um usuário é ídolo de outro.
     *
     * @param userLogin Login do usuário.
     * @param idolLogin Login do ídolo.
     * @return true se o ídolo for encontrado, false caso contrário.
     * @throws UserNotExistsException Se o usuário ou o ídolo não existirem.
     */
    public boolean isIdol(String userLogin, String idolLogin) throws UserNotExistsException {
        User user = userService.getUser(userLogin);
        User idol = userService.getUser(idolLogin);

        if (user == null || idol == null) {
            throw new UserNotExistsException();
        }

        return user.isIdol(idol.getLogin());
    }

    /**
     * Obtém a lista de fãs de um usuário.
     *
     * @param userLogin Login do usuário.
     * @return Uma string formatada contendo os fãs do usuário.
     * @throws UserNotExistsException Se o usuário não existir.
     */
    public String getFans(String userLogin) throws UserNotExistsException {
        User user = userService.getUser(userLogin);

        if (user == null) {
            throw new UserNotExistsException();
        }

        return ListFormatter.formatList(user.getFans());
    }

    /**
     * Verifica se um usuário é paquera de outro.
     *
     * @param userLogin Login do usuário.
     * @param crushLogin Login do paquera.
     * @return true se o paquera for encontrado, false caso contrário.
     * @throws UserNotExistsException Se o usuário ou o paquera não existirem.
     */
    public boolean isCrush(String userLogin, String crushLogin) throws UserNotExistsException {
        User user = userService.getUser(userLogin);
        User crush = userService.getUser(crushLogin);

        if (user == null || crush == null) {
            throw new UserNotExistsException();
        }

        return user.isCrush(crush.getLogin());
    }

    /**
     * Adiciona um paquera para o usuário.
     *
     * @param userLogin Login do usuário.
     * @param crushLogin Login do paquera.
     * @throws UserCannotBeCrushOfHimselfException Se o usuário tentar adicionar a si mesmo como paquera.
     * @throws UserAlreadyAddedAsCrushException Se o paquera já tiver sido adicionado.
     * @throws UserNotExistsException Se o usuário ou o paquera não existirem.
     * @throws EnemyUserException Se o paquera for um inimigo do usuário.
     */
    public void addCrush(String userLogin, String crushLogin) throws UserCannotBeCrushOfHimselfException, UserAlreadyAddedAsCrushException, UserNotExistsException, EnemyUserException {
        User user = userService.getUser(userLogin);
        User crush = userService.getUser(crushLogin);

        if (user == null || crush == null) {
            throw new UserNotExistsException();
        }

        if (userLogin.equals(crushLogin)) {
            throw new UserCannotBeCrushOfHimselfException();
        }

        if (isEnemy(crushLogin, userLogin)) {
            throw new EnemyUserException(crush.getNome());
        }

        if (user.isCrush(crush.getLogin())) {
            throw new UserAlreadyAddedAsCrushException();
        }

        user.addCrush(crush.getLogin());

        if (crush.getCrushs().contains(userLogin)) {
            String userMessageContent = crush.getNome() + " é seu paquera - Recado do Jackut.";
            String crushMessageContent = user.getNome() + " é seu paquera - Recado do Jackut.";

            Message userMessage = new Message(crushMessageContent, null, crush);
            Message crushMessage = new Message(userMessageContent, null, user);

            user.addMessage(crushMessage);
            crush.addMessage(userMessage);
        }
    }

    /**
     * Obtém a lista de paqueras de um usuário.
     *
     * @param userLogin Login do usuário.
     * @return Uma string formatada contendo os paqueras do usuário.
     * @throws UserNotExistsException Se o usuário não existir.
     */
    public String getCrushs(String userLogin) throws UserNotExistsException {
        User user = userService.getUser(userLogin);

        if (user == null) {
            throw new UserNotExistsException();
        }

        return ListFormatter.formatList(user.getCrushs());
    }

    /**
     * Verifica se um usuário é inimigo de outro.
     *
     * @param userLogin Login do usuário.
     * @param enemyLogin Login do inimigo.
     * @return true se o inimigo for encontrado, false caso contrário.
     * @throws UserNotExistsException Se o usuário ou o inimigo não existirem.
     */
    public boolean isEnemy(String userLogin, String enemyLogin) throws UserNotExistsException {
        User user = userService.getUser(userLogin);
        User enemy = userService.getUser(enemyLogin);

        if (user == null || enemy == null) {
            throw new UserNotExistsException();
        }

        return user.isEnemy(enemy.getLogin());
    }

    /**
     * Adiciona um inimigo para o usuário.
     *
     * @param userLogin Login do usuário.
     * @param enemyLogin Login do inimigo.
     * @throws UserNotExistsException Se o usuário ou o inimigo não existirem.
     * @throws UserCannotBeEnemyOfHimselfException Se o usuário tentar adicionar a si mesmo como inimigo.
     * @throws UserAlreadyAddedAsEnemyException Se o inimigo já tiver sido adicionado.
     */
    public void addEnemy(String userLogin, String enemyLogin) throws UserNotExistsException, UserCannotBeEnemyOfHimselfException, UserAlreadyAddedAsEnemyException {
        User user = userService.getUser(userLogin);
        User enemy = userService.getUser(enemyLogin);

        if (user == null || enemy == null) {
            throw new UserNotExistsException();
        }

        if (userLogin.equals(enemyLogin)) {
            throw new UserCannotBeEnemyOfHimselfException();
        }

        if (user.isEnemy(enemy.getLogin())) {
            throw new UserAlreadyAddedAsEnemyException();
        }

        user.addEnemy(enemy.getLogin());
    }
}