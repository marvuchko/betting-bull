package com.marvuchko.teamsandplayersservice.service;

import com.marvuchko.infrastructuremicroservice.service.RepositoryAwareService;
import com.marvuchko.teamsandplayersservice.data.entity.Player;
import com.marvuchko.teamsandplayersservice.data.repository.PlayerRepository;

public interface PlayerService extends RepositoryAwareService<Long, Player, PlayerRepository> {
}
