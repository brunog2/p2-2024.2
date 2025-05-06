package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.models.Message;
import br.ufal.ic.p2.jackut.models.User;
import br.ufal.ic.p2.jackut.utils.ListFormatter;

public class RelationshipService {
    UserService userService;

    public RelationshipService(UserService userService) {
        this.userService = userService;
    }

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

    public boolean isIdol(String userLogin, String idolLogin) throws UserNotExistsException {
        User user = userService.getUser(userLogin);
        User idol = userService.getUser(idolLogin);

        if (user == null || idol == null) {
            throw new UserNotExistsException();
        }

        return user.isIdol(idol.getLogin());
    }

    public String getFans(String userLogin) throws UserNotExistsException {
        User user = userService.getUser(userLogin);

        if (user == null) {
            throw new UserNotExistsException();
        }

        return ListFormatter.formatList(user.getFans());
    }

    public boolean isCrush(String userLogin, String crushLogin) throws UserNotExistsException {
        User user = userService.getUser(userLogin);
        User crush = userService.getUser(crushLogin);

        if (user == null || crush == null) {
            throw new UserNotExistsException();
        }

        return user.isCrush(crush.getLogin());
    }

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

    public String getCrushs(String userLogin) throws UserNotExistsException {
        User user = userService.getUser(userLogin);

        if (user == null) {
            throw new UserNotExistsException();
        }

        return ListFormatter.formatList(user.getCrushs());
    }

    public boolean isEnemy(String userLogin, String enemyLogin) throws UserNotExistsException {
        User user = userService.getUser(userLogin);
        User enemy = userService.getUser(enemyLogin);

        if (user == null || enemy == null) {
            throw new UserNotExistsException();
        }

        return user.isEnemy(enemy.getLogin());
    }

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
