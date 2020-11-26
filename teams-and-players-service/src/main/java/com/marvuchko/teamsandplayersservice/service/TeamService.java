package com.marvuchko.teamsandplayersservice.service;

import com.marvuchko.infrastructuremicroservice.service.RepositoryAwareService;
import com.marvuchko.teamsandplayersservice.data.entity.Team;
import com.marvuchko.teamsandplayersservice.data.repository.TeamRepository;

public interface TeamService extends RepositoryAwareService<Long, Team, TeamRepository> {

    Team addPlayerToTeam(Long id, Long playerId);

    Team removeFromTeam(Long id, Long playerId);

}
