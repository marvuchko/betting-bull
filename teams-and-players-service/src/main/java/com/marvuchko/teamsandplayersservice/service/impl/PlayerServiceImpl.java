package com.marvuchko.teamsandplayersservice.service.impl;

import com.marvuchko.infrastructuremicroservice.service.impl.RepositoryAwareServiceImpl;
import com.marvuchko.teamsandplayersservice.data.entity.Player;
import com.marvuchko.teamsandplayersservice.data.repository.PlayerRepository;
import com.marvuchko.teamsandplayersservice.service.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl extends RepositoryAwareServiceImpl<Long, Player, PlayerRepository>
        implements PlayerService {

    public PlayerServiceImpl(PlayerRepository repository) {
        super(repository);
    }
}
