package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.models.Community;
import br.ufal.ic.p2.jackut.models.User;
import br.ufal.ic.p2.jackut.repositories.CommunityRepository;
import br.ufal.ic.p2.jackut.utils.ListFormatter;

import java.util.List;

/**
 * Serviço responsável por gerenciar as comunidades no sistema Jackut.
 */
public class CommunityService {
    private final List<Community> communities;
    private final UserLookupService userLookupService;
    private final CommunityRepository communityRepository;

    /**
     * Construtor da classe CommunityService.
     *
     * @param userLookupService Serviço de busca de usuários.
     */
    public CommunityService(UserLookupService userLookupService) {
        this.communityRepository = new CommunityRepository();
        this.communities = communityRepository.getCommunities();
        this.userLookupService = userLookupService;
    }

    /**
     * Cria uma nova comunidade.
     *
     * @param name        Nome da comunidade.
     * @param description Descrição da comunidade.
     * @param user        Login do usuário criador da comunidade.
     * @throws CommunityAlreadyExistsException Se já existir uma comunidade com o mesmo nome.
     */
    public void createCommunity(String name, String description, String user) throws CommunityAlreadyExistsException {
        User owner = this.userLookupService.getUser(user);

        if (communities.stream().anyMatch(community -> community.getName().equals(name))) {
            throw new CommunityAlreadyExistsException();
        }

        Community community = new Community(name, description, owner);

        communities.add(community);
        owner.addCommunity(name);
    }

    /**
     * Obtém uma comunidade pelo nome.
     *
     * @param name Nome da comunidade.
     * @return A comunidade correspondente.
     * @throws CommunityNotExistsException Se a comunidade não existir.
     */
    public Community getCommunity(String name) throws CommunityNotExistsException {
        return communities.stream()
                .filter(community -> community.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new CommunityNotExistsException());
    }

    /**
     * Obtém a descrição de uma comunidade.
     *
     * @param name Nome da comunidade.
     * @return A descrição da comunidade.
     * @throws CommunityNotExistsException Se a comunidade não existir.
     */
    public String getCommunityDescription(String name) throws CommunityNotExistsException {
        return this.getCommunity(name).getDescription();
    }

    /**
     * Obtém o login do dono de uma comunidade.
     *
     * @param name Nome da comunidade.
     * @return O login do dono da comunidade.
     * @throws CommunityNotExistsException Se a comunidade não existir.
     */
    public String getCommunityOwner(String name) throws CommunityNotExistsException {
        return this.getCommunity(name).getOwner().getLogin();
    }

    /**
     * Obtém os membros de uma comunidade.
     *
     * @param name Nome da comunidade.
     * @return Uma string formatada contendo os membros da comunidade.
     * @throws CommunityNotExistsException Se a comunidade não existir.
     */
    public String getCommunityMembers(String name) throws CommunityNotExistsException {
        return ListFormatter.formatList(this.getCommunity(name).getMembers());
    }

    /**
     * Salva as comunidades no repositório.
     */
    public void saveCommunities() {
        this.communityRepository.saveCommunities(this.communities);
    }

    /**
     * Remove todas as comunidades.
     */
    public void removeAllCommunities() {
        this.communities.clear();
        this.saveCommunities();
    }

    /**
     * Adiciona um membro a uma comunidade.
     *
     * @param communityName Nome da comunidade.
     * @param userName      Login do usuário a ser adicionado.
     * @throws CommunityNotExistsException Se a comunidade não existir.
     * @throws UserNotExistsException Se o usuário não existir.
     * @throws UserAlreadyMemberOfCommunityException Se o usuário já for membro da comunidade.
     */
    public void addMember(String communityName, String userName) throws CommunityNotExistsException, UserNotExistsException, UserAlreadyMemberOfCommunityException {
        Community community = this.getCommunity(communityName);
        User user = this.userLookupService.getUser(userName);

        if (user == null) {
            throw new UserNotExistsException();
        }

        if (community.getMembers().contains(user.getLogin())) {
            throw new UserAlreadyMemberOfCommunityException();
        }

        community.addMember(user);
        community.registerObserver(user);
        user.addCommunity(communityName);
    }

    /**
     * Envia uma mensagem para os membros de uma comunidade.
     *
     * @param sessionId     ID da sessão do usuário remetente.
     * @param communityName Nome da comunidade.
     * @param message       Conteúdo da mensagem.
     * @throws Exception Se ocorrer algum erro durante o envio.
     */
    public void sendMessage(String sessionId, String communityName, String message) throws Exception {
        User user = userLookupService.getUser(sessionId);

        if (user == null) {
            throw new UserNotExistsException();
        }

        Community community = this.getCommunity(communityName);
        community.notifyObservers(message);
    }

    /**
     * Remove uma comunidade.
     *
     * @param name Nome da comunidade.
     * @throws CommunityNotExistsException Se a comunidade não existir.
     */
    public void removeCommunity(String name) throws CommunityNotExistsException {
        Community community = this.getCommunity(name);

        // Remove a comunidade da lista de comunidades
        communities.remove(community);

        // Salva a lista atualizada de comunidades
        this.saveCommunities();
    }
}