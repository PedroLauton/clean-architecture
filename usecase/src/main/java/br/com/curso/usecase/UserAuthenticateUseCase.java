package br.com.curso.usecase;

public interface UserAuthenticateUseCase {
    Boolean authenticate(String username, String password);
}
