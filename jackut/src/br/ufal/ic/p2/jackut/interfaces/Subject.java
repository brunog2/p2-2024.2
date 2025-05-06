package br.ufal.ic.p2.jackut.interfaces;

/**
 * Interface que define o comportamento de um sujeito (Subject) no padrão de projeto Observer.
 * Um sujeito pode registrar, remover e notificar observadores.
 */
public interface Subject {

    /**
     * Registra um observador para ser notificado de mudanças no sujeito.
     *
     * @param observer O observador a ser registrado.
     */
    void registerObserver(Observer observer);

    /**
     * Remove um observador previamente registrado.
     *
     * @param observer O observador a ser removido.
     */
    void removeObserver(Observer observer);

    /**
     * Notifica todos os observadores registrados com uma mensagem.
     *
     * @param message A mensagem a ser enviada aos observadores.
     */
    void notifyObservers(String message);
}