package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.models.User;

public interface UserLookupService {
    User getUser(String login);
}