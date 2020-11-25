package com.marvuchko.teamsandplayersservice.service.impl;

import com.marvuchko.infrastructuremicroservice.service.impl.RepositoryAwareServiceImpl;
import com.marvuchko.teamsandplayersservice.data.entity.Team;
import com.marvuchko.teamsandplayersservice.data.repository.TeamRepository;
import com.marvuchko.teamsandplayersservice.service.TeamService;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl extends RepositoryAwareServiceImpl<Long, Team, TeamRepository>
        implements TeamService {

    public TeamServiceImpl(TeamRepository repository) {
        super(repository);
    }
}
