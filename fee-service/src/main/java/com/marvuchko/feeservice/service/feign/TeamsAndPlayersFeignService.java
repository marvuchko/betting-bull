package com.marvuchko.feeservice.service.feign;

import com.marvuchko.feeservice.dto.feign.PlayerDto;
import com.marvuchko.feeservice.dto.feign.TeamDto;
import com.marvuchko.infrastructuremicroservice.service.feign.BaseFeignService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = TeamsAndPlayersFeignService.REMOTE_MICROSERVICE)
public interface TeamsAndPlayersFeignService extends BaseFeignService {

    String REMOTE_MICROSERVICE = "teams-and-players-service";

    String BASE_URL_PLAYER = "/player";

    String BASE_URL_TEAM = "/team";

    @GetMapping(BASE_URL_PLAYER + ID_PATH)
    PlayerDto getPlayerById(@PathVariable Long id);

    @GetMapping(BASE_URL_TEAM + ID_PATH)
    TeamDto getTeamById(@PathVariable Long id);

}
