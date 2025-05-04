package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.exceptions.CommunityException;
import br.ufal.ic.p2.jackut.exceptions.UserException;
import br.ufal.ic.p2.jackut.models.Community;
import br.ufal.ic.p2.jackut.models.User;
import br.ufal.ic.p2.jackut.repositories.CommunityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CommunityService {
    private final List<Community> communities;
    private final UserService userService;
    private final CommunityRepository communityRepository;

    public CommunityService(UserService userService) {
        this.communityRepository = new CommunityRepository();
        this.communities = communityRepository.getCommunities();
        this.userService = userService;
    }

    public void createCommunity(String name, String description, String user) throws CommunityException {
        User owner = this.userService.getUser(user);

        if (communities.stream().anyMatch(community -> community.getName().equals(name))) {
            throw new CommunityException("Comunidade com esse nome já existe.");
        }

        Community community = new Community(name, description, owner);

        communities.add(community);
        owner.addCommunity(name);
    }

    public Community getCommunity(String name) throws CommunityException {
        return communities.stream()
                .filter(community -> community.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new CommunityException("Comunidade não existe."));
    }

    public String getCommunityDescription(String name) throws CommunityException {
        return this.getCommunity(name).getDescription();
    }

    public String getCommunityOwner(String name) throws CommunityException {
        return this.getCommunity(name).getOwner().getLogin();
    }

    public String getCommunityMembers(String name) throws CommunityException {
        List<String> members = this.getCommunity(name).getMembers();

        return "{" + String.join(",", members) + "}";
    }

    public void saveCommunities() {
        this.communityRepository.saveCommunities(this.communities);
    }

    public void removeAllCommunities() {
        this.communities.clear();
        this.saveCommunities();
    }

    public void addMember(String communityName, String userName) throws Exception {
        Community community = this.getCommunity(communityName);
        User user = this.userService.getUser(userName);

        if (user == null) {
            throw new UserException("Usuário não cadastrado.");
        }

        if (community.getMembers().contains(user.getLogin())) {
            throw new CommunityException("Usuario já faz parte dessa comunidade.");
        }

        community.addMember(user);
        user.addCommunity(communityName);
    }


}
