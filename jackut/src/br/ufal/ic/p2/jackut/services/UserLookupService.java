package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.models.User;
import br.ufal.ic.p2.jackut.exceptions.UserException;

public interface UserLookupService {
    User getUser(String login) throws UserException;
}