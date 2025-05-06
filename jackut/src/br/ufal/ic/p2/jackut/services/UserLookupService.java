package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.models.User;

/**
 * Interface responsável por definir o contrato para busca de usuários no sistema Jackut.
 */
public interface UserLookupService {

    /**
     * Busca um usuário pelo login.
     *
     * @param login Nome de usuário.
     * @return O objeto User correspondente ao login, ou null caso não seja encontrado.
     */
    User getUser(String login);
}