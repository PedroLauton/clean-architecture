package br.com.curso.application.gateway;

import br.com.curso.core.domain.User;

public interface CreateUserGateway {

    User create(User user);
}
