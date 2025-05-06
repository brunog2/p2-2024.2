package br.ufal.ic.p2.jackut.interfaces;

/**
 * Interface que define o comportamento de um observador (Observer) no padrão de projeto Observer.
 * Um observador é notificado de mudanças em um sujeito (Subject) ao qual está registrado.
 */
public interface Observer {

    /**
     * Método chamado para notificar o observador de uma mudança.
     *
     * @param message A mensagem enviada pelo sujeito para notificar o observador.
     */
    void update(String message);
}