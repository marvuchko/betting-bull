package teamsandplayersservice.service;

import com.marvuchko.teamsandplayersservice.data.entity.Team;
import com.marvuchko.teamsandplayersservice.data.repository.TeamRepository;
import com.marvuchko.teamsandplayersservice.dto.feign.ContractFeeDto;
import com.marvuchko.teamsandplayersservice.service.feign.ContractFeeFeignService;
import com.marvuchko.teamsandplayersservice.service.impl.TeamServiceImpl;
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
public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private ContractFeeFeignService contractFeeFeignService;

    @InjectMocks
    private TeamServiceImpl teamService;

    @Test
    void shouldAlsoDeleteContractOnPlayerDeletion() {
        final var team = new Team();
        team.setId(1l);

        final var contract = new ContractFeeDto();
        contract.setId(1l);
        contract.setPlayerId(1l);

        given(teamRepository.findById(1l)).willReturn(ofNullable(team));
        given(contractFeeFeignService.deleteByTeam(1l))
                .willReturn(Set.of(contract));

        var deletedTeam = teamService.delete(1l);

        assertThat(deletedTeam).isNotNull();
    }

}
