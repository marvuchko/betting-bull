package com.marvuchko.feeservice.resource;

import com.marvuchko.feeservice.data.entity.ContractFee;
import com.marvuchko.feeservice.dto.ContractFeeDto;
import com.marvuchko.feeservice.service.ContractFeeService;
import com.marvuchko.feeservice.service.feign.TeamsAndPlayersFeignService;
import com.marvuchko.infrastructuremicroservice.resource.ServiceAwareResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@Api(tags = ContractFeeResource.TAG_NAME)
@RequestMapping(ContractFeeResource.BASE_URL)
public class ContractFeeResource extends ServiceAwareResource<ContractFeeService> {

    public static final String BASE_URL = "/contract-fee";
    private static final String DELETE_BY_PLAYER_PATH = "/player/{id}";
    private static final String DELETE_BY_TEAM_PATH = "/team/{id}";

    public static final String TAG_NAME = "Contract Fee Resource";
    public static final String TAG_DESCRIPTION = "Allows managing data about contracts between players and teams.";

    private final TeamsAndPlayersFeignService teamsAndPlayersFeignService;

    protected ContractFeeResource(
            ContractFeeService service,
            TeamsAndPlayersFeignService teamsAndPlayersFeignService
    ) {
        super(service);
        this.teamsAndPlayersFeignService = teamsAndPlayersFeignService;
    }

    @GetMapping
    @ApiOperation("Returns information about contract fee, with pagination.")
    public Page<ContractFeeDto> getPage(
            @PageableDefault(size = DEFAULT_PAGE_SIZE, sort = { ID_FIELD }, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return mapPage(service.getPage(pageable), ContractFeeDto.class);
    }

    @GetMapping(ID_PATH)
    @ApiOperation("Returns single contract fee record by ID.")
    public ContractFeeDto getById(@PathVariable Long id) {
        return map(service.getById(id), ContractFeeDto.class);
    }

    @PostMapping
    @ApiOperation("Creates new contract fee record.")
    public ContractFeeDto create(@Valid @RequestBody ContractFeeDto body) {
        var entity = map(body, ContractFee.class);
        return map(service.create(entity), ContractFeeDto.class);
    }

    @DeleteMapping(ID_PATH)
    @ApiOperation("Deletes contract fee record by ID.")
    public ContractFeeDto deleteById(@PathVariable Long id) {
        return map(service.delete(id), ContractFeeDto.class);
    }

    @DeleteMapping(DELETE_BY_PLAYER_PATH)
    @ApiOperation("Deletes all records for specific player.")
    public Set<ContractFeeDto> deleteByPlayer(@PathVariable Long id) {
        return mapSet(service.deleteByPlayer(id), ContractFeeDto.class);
    }

    @DeleteMapping(DELETE_BY_TEAM_PATH)
    @ApiOperation("Deletes all records for specific team.")
    public Set<ContractFeeDto> deleteByTeam(@PathVariable Long id) {
        return mapSet(service.deleteByTeam(id), ContractFeeDto.class);
    }

}
