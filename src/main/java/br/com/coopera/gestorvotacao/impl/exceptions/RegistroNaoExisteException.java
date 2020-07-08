package br.com.coopera.gestorvotacao.impl.exceptions;

public class RegistroNaoExisteException extends RuntimeException {

    public RegistroNaoExisteException(String message) {
        super(message);
    }
}
