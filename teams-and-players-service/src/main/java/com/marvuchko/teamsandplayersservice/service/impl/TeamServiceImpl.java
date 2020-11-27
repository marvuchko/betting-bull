package com.marvuchko.teamsandplayersservice.service.impl;

import com.marvuchko.infrastructuremicroservice.service.impl.RepositoryAwareServiceImpl;
import com.marvuchko.teamsandplayersservice.data.entity.Team;
import com.marvuchko.teamsandplayersservice.data.repository.TeamRepository;
import com.marvuchko.teamsandplayersservice.service.PlayerService;
import com.marvuchko.teamsandplayersservice.service.TeamService;
import com.marvuchko.teamsandplayersservice.service.feign.ContractFeeFeignService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;

import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

@Service
public class TeamServiceImpl extends RepositoryAwareServiceImpl<Long, Team, TeamRepository>
        implements TeamService {

    private final ContractFeeFeignService contractFeeFeignService;

    private final PlayerService playerService;

    public TeamServiceImpl(
            TeamRepository repository,
            ContractFeeFeignService contractFeeFeignService,
            PlayerService playerService
    ) {
        super(repository);
        this.contractFeeFeignService = contractFeeFeignService;
        this.playerService = playerService;
    }

    @Override
    public Team delete(Long id) {
        var entity = super.delete(id);
        contractFeeFeignService.deleteByTeam(entity.getId());
        return entity;
    }

    @Override
    public Team create(Team entity) {
        if (nonNull(entity.getPlayers())) {
            entity.getPlayers().forEach(player -> player.setTeam(entity));
        }
        return super.create(entity);
    }

    @Override
    public Team addPlayerToTeam(Long id, Long playerId) {
        var team = getById(id);
        var player = playerService.getById(playerId);

        var players = ofNullable(team.getPlayers()).orElse(new LinkedHashSet<>());
        players.add(player);
        player.setTeam(team);

        team.setPlayers(players);
        return repository.save(team);
    }

    @Override
    public Team removeFromTeam(Long id, Long playerId) {
        var team = getById(id);
        var player = playerService.getById(playerId);

        var players = ofNullable(team.getPlayers()).orElse(new LinkedHashSet<>());
        players.remove(player);
        player.setTeam(null);

        team.setPlayers(players);
        return repository.save(team);
    }
}
