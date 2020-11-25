package com.marvuchko.feeservice.resource;

import com.marvuchko.feeservice.data.entity.ContractFee;
import com.marvuchko.feeservice.dto.ContractFeeDto;
import com.marvuchko.feeservice.service.ContractFeeService;
import com.marvuchko.feeservice.service.feign.TeamsAndPlayersFeignService;
import com.marvuchko.infrastructuremicroservice.resource.ServiceAwareResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ContractFeeResource.BASE_URL)
public class ContractFeeResource extends ServiceAwareResource<ContractFeeService> {

    public static final String BASE_URL = "/contract-fee";

    private final TeamsAndPlayersFeignService teamsAndPlayersFeignService;

    protected ContractFeeResource(
            ContractFeeService service,
            TeamsAndPlayersFeignService teamsAndPlayersFeignService
    ) {
        super(service);
        this.teamsAndPlayersFeignService = teamsAndPlayersFeignService;
    }

    @GetMapping
    public Page<ContractFeeDto> getPage(
            @PageableDefault(size = 20, sort = { ID_FIELD }, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return mapPage(service.getPage(pageable), ContractFeeDto.class);
    }

    @GetMapping(ID_PATH)
    public ContractFeeDto getById(@PathVariable Long id) {
        var dto = map(service.getById(id), ContractFeeDto.class);
        dto.setPlayer(teamsAndPlayersFeignService.getPlayerById(dto.getPlayerId()));
        dto.setTeam(teamsAndPlayersFeignService.getTeamById(dto.getTeamId()));
        return dto;
    }

    @PostMapping
    public ContractFeeDto create(@Valid @RequestBody ContractFeeDto body) {
        var entity = map(body, ContractFee.class);
        return map(service.create(entity), ContractFeeDto.class);
    }

    @DeleteMapping
    public ContractFeeDto deleteById(@PathVariable Long id) {
        return map(service.delete(id), ContractFeeDto.class);
    }

}
