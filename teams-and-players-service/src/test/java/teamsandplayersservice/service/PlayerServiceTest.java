package teamsandplayersservice.service;

import com.marvuchko.teamsandplayersservice.data.entity.Player;
import com.marvuchko.teamsandplayersservice.data.repository.PlayerRepository;
import com.marvuchko.teamsandplayersservice.dto.feign.ContractFeeDto;
import com.marvuchko.teamsandplayersservice.service.feign.ContractFeeFeignService;
import com.marvuchko.teamsandplayersservice.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static java.util.Optional.ofNullable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @Mock
    private ContractFeeFeignService contractFeeFeignService;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    void shouldAlsoDeleteContractOnPlayerDeletion() {
        final var player = new Player();
        player.setId(1l);

        final var contract = new ContractFeeDto();
        contract.setId(1l);
        contract.setPlayerId(1l);

        given(playerRepository.findById(1l)).willReturn(ofNullable(player));
        given(contractFeeFeignService.deleteByPlayer(1l))
                .willReturn(Set.of(contract));

        var deletedPlayer = playerService.delete(1l);

        assertThat(deletedPlayer).isNotNull();
    }

}
