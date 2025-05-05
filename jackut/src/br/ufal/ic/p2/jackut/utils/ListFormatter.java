package br.ufal.ic.p2.jackut.utils;

public class ListFormatter {
    /**
     * Formata uma lista de strings em uma única string, separando os elementos por vírgula.
     *
     * @param list A lista de strings a ser formatada.
     * @return Uma string contendo os elementos da lista separados por vírgula.
     */
    public static String formatList(java.util.List<String> list) {
        return String.join(", ", list);
    }
}
