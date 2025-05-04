package br.ufal.ic.p2.jackut.repositories;

import br.ufal.ic.p2.jackut.models.Community;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositório responsável por gerenciar a persistência de comunidades no sistema Jackut.
 */
public class CommunityRepository {
    private static final String FILE_NAME = "Communities.ser";

    /**
     * Obtém a lista de comunidades armazenados no repositório.
     *
     * @return Lista de comunidades.
     */
    public List<Community> getCommunities() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = objectInputStream.readObject();
            if (obj instanceof List<?>) {
                return (List<Community>) obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Salva a lista de comunidades no repositório.
     *
     * @param comunities Lista de comunidades a ser salva.
     */
    public void saveCommunities(List<Community> comunities) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(comunities);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}