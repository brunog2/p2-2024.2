package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.models.Community;
import br.ufal.ic.p2.jackut.models.User;
import br.ufal.ic.p2.jackut.repositories.CommunityRepository;
import br.ufal.ic.p2.jackut.utils.ListFormatter;

import java.util.List;

public class CommunityService {
    private final List<Community> communities;
    private final UserLookupService userLookupService;
    private final CommunityRepository communityRepository;

    public CommunityService(UserLookupService userLookupService) {
        this.communityRepository = new CommunityRepository();
        this.communities = communityRepository.getCommunities();
        this.userLookupService = userLookupService;
    }

    public void createCommunity(String name, String description, String user) throws CommunityAlreadyExistsException {
        User owner = this.userLookupService.getUser(user);

        if (communities.stream().anyMatch(community -> community.getName().equals(name))) {
            throw new CommunityAlreadyExistsException();
        }

        Community community = new Community(name, description, owner);

        communities.add(community);
        owner.addCommunity(name);
    }

    public Community getCommunity(String name) throws CommunityNotExistsException {
        return communities.stream()
                .filter(community -> community.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new CommunityNotExistsException());
    }

    public String getCommunityDescription(String name) throws CommunityNotExistsException {
        return this.getCommunity(name).getDescription();
    }

    public String getCommunityOwner(String name) throws CommunityNotExistsException {
        return this.getCommunity(name).getOwner().getLogin();
    }

    public String getCommunityMembers(String name) throws CommunityNotExistsException {
        return ListFormatter.formatList(this.getCommunity(name).getMembers());
    }

    public void saveCommunities() {
        this.communityRepository.saveCommunities(this.communities);
    }

    public void removeAllCommunities() {
        this.communities.clear();
        this.saveCommunities();
    }

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

    public void sendMessage(
            String sessionId,
            String communityName,
            String message
    ) throws Exception {
        User user = userLookupService.getUser(sessionId);

        if (user == null) {
            throw new UserNotExistsException();
        }

        // Verifica se o usuário faz parte da comunidade
//        if (user.getCommunities().stream()
//                .filter(community -> community.equals(communityName))
//                .findAny()
//                .isEmpty()) {
//            throw new CommunityException("Usuário não faz parte dessa comunidade.");
//        }

        Community community = this.getCommunity(communityName);
        community.notifyObservers(message);
    }

    public void removeCommunity(String name) throws CommunityNotExistsException {
        Community community = this.getCommunity(name);


        // Remove the community from the list of communities
        communities.remove(community);

        // Save the updated list of communities
        this.saveCommunities();
    }
}
