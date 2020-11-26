package com.marvuchko.teamsandplayersservice.service.impl;

import com.marvuchko.infrastructuremicroservice.service.impl.RepositoryAwareServiceImpl;
import com.marvuchko.teamsandplayersservice.data.entity.Player;
import com.marvuchko.teamsandplayersservice.data.repository.PlayerRepository;
import com.marvuchko.teamsandplayersservice.service.PlayerService;
import com.marvuchko.teamsandplayersservice.service.feign.ContractFeeFeignService;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl extends RepositoryAwareServiceImpl<Long, Player, PlayerRepository>
        implements PlayerService {

    private final ContractFeeFeignService contractFeeFeignService;

    public PlayerServiceImpl(PlayerRepository repository, ContractFeeFeignService contractFeeFeignService) {
        super(repository);
        this.contractFeeFeignService = contractFeeFeignService;
    }

    @Override
    public Player delete(Long id) {
        var entity = super.delete(id);
        contractFeeFeignService.deleteByPlayer(entity.getId());
        return entity;
    }

}
