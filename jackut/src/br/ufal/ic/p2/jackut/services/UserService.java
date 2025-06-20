package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.models.Community;
import br.ufal.ic.p2.jackut.models.User;
import br.ufal.ic.p2.jackut.repositories.UserRepository;
import br.ufal.ic.p2.jackut.validators.UserValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Serviço responsável pela gestão de usuários no sistema Jackut.
 */
public class UserService implements UserLookupService {
    private List<User> users;
    private final UserRepository userRepository;
    private final UserAttributeManager userAttributeManager;
    private final SessionService sessionService;
    private CommunityService communityService;

    /**
     * Construtor do UserService.
     *
     * @param userRepository       Repositório de usuários.
     * @param userAttributeManager Gerenciador de atributos de usuário.
     * @param sessionService       Serviço de gerenciamento de sessões.
     */
    public UserService(UserRepository userRepository, UserAttributeManager userAttributeManager, SessionService sessionService) {
        this.userRepository = userRepository;
        this.userAttributeManager = userAttributeManager;
        this.sessionService = sessionService;
        this.users = userRepository.getUsers();
    }

    /**
     * Define o serviço de comunidades.
     *
     * @param communityService Serviço de comunidades.
     */
    public void setCommunityService(CommunityService communityService) {
        this.communityService = communityService;
    }

    /**
     * Cria um novo usuário no sistema.
     *
     * @param login Nome de usuário único.
     * @param senha Senha do usuário.
     * @param nome  Nome real do usuário.
     * @throws UserAlreadyExistsException Se o login já estiver em uso.
     * @throws InvalidLoginException Se o login for inválido.
     * @throws InvalidPasswordException Se a senha for inválida.
     */
    public void criarUsuario(String login, String senha, String nome) throws UserAlreadyExistsException, InvalidLoginException, InvalidPasswordException {
        UserValidator.validateNewUser(login, senha, nome, users);
        User user = new User(login, senha, nome);
        users.add(user);
    }

    /**
     * Abre uma sessão para um usuário autenticado.
     *
     * @param login Nome de usuário.
     * @param senha Senha do usuário.
     * @return O login do usuário autenticado.
     * @throws InvalidLoginOrPasswordException Se o login ou senha forem inválidos.
     */
    public String abrirSessao(String login, String senha) throws InvalidLoginOrPasswordException {
        User user = UserValidator.validateLogin(login, senha, users);
        sessionService.addSession(user);
        return login;
    }

    /**
     * Obtém o valor de um atributo do usuário.
     *
     * @param login    Nome de usuário.
     * @param atributo Nome do atributo.
     * @return O valor do atributo.
     * @throws UserNotExistsException Se o usuário não existir.
     * @throws AttributeNotFilledException Se o atributo não estiver preenchido.
     */
    public String getAtributoUsuario(String login, String atributo) throws UserNotExistsException, AttributeNotFilledException {
        User user = getUser(login);

        if (user == null) throw new UserNotExistsException();

        String attributeValue = this.userAttributeManager.getAtributo(user, atributo);
        if (attributeValue == null) {
            throw new AttributeNotFilledException();
        }

        return attributeValue;
    }

    /**
     * Edita um atributo do perfil do usuário.
     *
     * @param id       ID do usuário.
     * @param atributo Nome do atributo a ser editado.
     * @param valor    Novo valor do atributo.
     * @throws UserNotExistsException Se o usuário não existir.
     */
    public void editarPerfil(String id, String atributo, String valor) throws UserNotExistsException {
        User user = getUser(id);
        if (user == null) {
            throw new UserNotExistsException();
        }
        this.userAttributeManager.setAtributo(user, atributo, valor);
    }

    /**
     * Busca um usuário pelo login.
     *
     * @param login Nome de usuário.
     * @return O objeto User se encontrado, ou null caso contrário.
     */
    @Override
    public User getUser(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Obtém as comunidades das quais o usuário é membro.
     *
     * @param login Nome de usuário.
     * @return Uma string contendo os nomes das comunidades.
     * @throws UserNotExistsException Se o usuário não existir.
     */
    public String getMemberCommunities(String login) throws UserNotExistsException {
        User user = getUser(login);

        if (user == null) {
            throw new UserNotExistsException();
        }

        return "{" + String.join(",", user.getCommunities()) + "}";
    }

    /**
     * Lê uma mensagem de comunidade para o usuário.
     *
     * @param login Nome de usuário.
     * @return A mensagem da comunidade.
     * @throws EmptyCommunityMessagesException Se não houver mensagens na comunidade.
     * @throws UserNotExistsException Se o usuário não existir.
     */
    public String readCommunityMessage(String login) throws EmptyCommunityMessagesException, UserNotExistsException {
        User user = getUser(login);

        if (user == null) {
            throw new UserNotExistsException();
        }

        String message = user.readCommunityMessage();

        if (message == null) {
            throw new EmptyCommunityMessagesException();
        }

        return message;
    }

    /**
     * Remove um usuário do sistema.
     *
     * @param login Nome de usuário.
     * @throws UserNotExistsException Se o usuário não existir.
     * @throws CommunityNotExistsException Se alguma comunidade associada não existir.
     */
    public void removeUser(String login) throws UserNotExistsException, CommunityNotExistsException {
        User user = getUser(login);
        if (user == null) {
            throw new UserNotExistsException();
        }

        // Remove o usuário de todas as comunidades e relacionamentos
        for (String communityName : user.getCommunities()) {
            try {
                Community community = communityService.getCommunity(communityName);

                for (String member : community.getMembers()) {
                    User memberUser = getUser(member);
                    if (memberUser != null && !memberUser.getLogin().equals(login)) {
                        memberUser.removeCommunity(communityName);
                    }
                }

                if (community.getOwner().getLogin().equals(login)) {
                    communityService.removeCommunity(communityName);

                } else {
                    community.removeMember(user);
                    user.removeCommunity(communityName);
                }
            } catch (CommunityNotExistsException ignored) {
                // Comunidade pode já ter sido removida
            }
        }

        user.getCommunities().clear();

        for (User friend : user.getFriends()) {
            friend.getFriends().remove(user);
        }
        for (String fan : user.getFans()) {
            User fanUser = getUser(fan);
            if (fanUser != null) {
                fanUser.getIdols().remove(login);
            }
        }
        for (String idol : user.getIdols()) {
            User idolUser = getUser(idol);
            if (idolUser != null) {
                idolUser.getFans().remove(login);
            }
        }
        for (String crush : user.getCrushs()) {
            User crushUser = getUser(crush);
            if (crushUser != null) {
                crushUser.getCrushs().remove(login);
            }
        }
        for (String enemy : user.getEnemies()) {
            User enemyUser = getUser(enemy);
            if (enemyUser != null) {
                enemyUser.getEnemies().remove(login);
            }
        }

        user.getMessages().clear();

        for (User receiver : users) {
            receiver.getMessages().removeIf(message -> message.getSender() != null && message.getSender().getLogin().equals(login));
        }

        users.remove(user);
        sessionService.removeSessions();
        userRepository.saveUsers(users);
        communityService.saveCommunities();
    }

    /**
     * Reseta o sistema, removendo todos os usuários e sessões.
     */
    public void zerarSistema() {
        this.users = new ArrayList<>();
        this.sessionService.removeSessions();
    }

    /**
     * Encerra o sistema, salvando os usuários no repositório.
     */
    public void encerrarSistema() {
        userRepository.saveUsers(users);
    }
}