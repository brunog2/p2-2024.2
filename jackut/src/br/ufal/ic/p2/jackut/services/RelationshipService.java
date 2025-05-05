package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.exceptions.UserException;
import br.ufal.ic.p2.jackut.models.Message;
import br.ufal.ic.p2.jackut.models.User;

public class RelationshipService {
    UserService userService;

    public RelationshipService(UserService userService) {
        this.userService = userService;
    }

    public void addIdol(String userLogin, String idolLogin) throws UserException {
        User user = userService.getUser(userLogin);
        User idol = userService.getUser(idolLogin);


        if (user == null || idol == null) {
            throw new UserException("Usuário não cadastrado.");
        }

        if (userLogin.equals(idolLogin)) {
            throw new UserException("Usuário não pode ser fã de si mesmo.");
        }


        if (isEnemy(idolLogin, userLogin)) {
            throw new UserException("Função inválida: " + idol.getNome() + " é seu inimigo.");
        }

        if (user.isIdol(idol.getLogin())) {
            throw new UserException("Usuário já está adicionado como ídolo.");
        }

        user.addIdol(idol.getLogin());
        idol.addFan(user.getLogin());
    }

    public boolean isIdol(String userLogin, String idolLogin) throws UserException {
        User user = userService.getUser(userLogin);
        User idol = userService.getUser(idolLogin);

        if (user == null || idol == null) {
            throw new UserException("Usuário não cadastrado.");
        }

        return user.isIdol(idol.getLogin());
    }

    public String getFans(String userLogin) throws UserException {
        User user = userService.getUser(userLogin);

        if (user == null) {
            throw new UserException("Usuário não cadastrado.");
        }

        return user.getFans();
    }

    public boolean isCrush(String userLogin, String crushLogin) throws UserException {
        User user = userService.getUser(userLogin);
        User crush = userService.getUser(crushLogin);

        if (user == null || crush == null) {
            throw new UserException("Usuário não cadastrado.");
        }

        return user.isCrush(crush.getLogin());
    }

    public void addCrush(String userLogin, String crushLogin) throws UserException {
        User user = userService.getUser(userLogin);
        User crush = userService.getUser(crushLogin);

        if (user == null || crush == null) {
            throw new UserException("Usuário não cadastrado.");
        }

        if (userLogin.equals(crushLogin)) {
            throw new UserException("Usuário não pode ser paquera de si mesmo.");
        }

        if (isEnemy(crushLogin, userLogin)) {
            throw new UserException("Função inválida: " + crush.getNome() + " é seu inimigo.");
        }

        if (user.isCrush(crush.getLogin())) {
            throw new UserException("Usuário já está adicionado como paquera.");
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

    public String getCrushs(String userLogin) throws UserException {
        User user = userService.getUser(userLogin);

        if (user == null) {
            throw new UserException("Usuário não cadastrado.");
        }

        return user.getCrushs();
    }

    public boolean isEnemy(String userLogin, String enemyLogin) throws UserException {
        User user = userService.getUser(userLogin);
        User enemy = userService.getUser(enemyLogin);

        if (user == null || enemy == null) {
            throw new UserException("Usuário não cadastrado.");
        }

        return user.isEnemy(enemy.getLogin());
    }

    public void addEnemy(String userLogin, String enemyLogin) throws UserException {
        User user = userService.getUser(userLogin);
        User enemy = userService.getUser(enemyLogin);

        if (user == null || enemy == null) {
            throw new UserException("Usuário não cadastrado.");
        }

        if (userLogin.equals(enemyLogin)) {
            throw new UserException("Usuário não pode ser inimigo de si mesmo.");
        }

        if (user.isEnemy(enemy.getLogin())) {
            throw new UserException("Usuário já está adicionado como inimigo.");
        }

        user.addEnemy(enemy.getLogin());
    }


}
