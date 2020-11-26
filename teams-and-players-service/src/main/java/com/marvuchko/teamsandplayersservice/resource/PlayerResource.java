package com.marvuchko.teamsandplayersservice.resource;

import com.marvuchko.infrastructuremicroservice.resource.ServiceAwareResource;
import com.marvuchko.teamsandplayersservice.data.entity.Player;
import com.marvuchko.teamsandplayersservice.dto.PlayerDto;
import com.marvuchko.teamsandplayersservice.service.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = PlayerResource.TAG_NAME)
@RequestMapping(PlayerResource.BASE_URL)
public class PlayerResource extends ServiceAwareResource<PlayerService> {

    public static final String BASE_URL = "/player";
    public static final String TAG_NAME = "Player Resource";
    public static final String TAG_DESCRIPTION = "Allows managing data about players.";

    protected PlayerResource(PlayerService service) {
        super(service);
    }

    @GetMapping
    @ApiOperation("Returns information about players, with pagination.")
    public Page<PlayerDto> getPage(
            @PageableDefault(size = DEFAULT_PAGE_SIZE, sort = { ID_FIELD }, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return mapPage(service.getPage(pageable), PlayerDto.class);
    }

    @GetMapping(ID_PATH)
    @ApiOperation("Returns single player record by ID.")
    public PlayerDto getById(@PathVariable Long id) {
        return map(service.getById(id), PlayerDto.class);
    }

    @PostMapping
    @ApiOperation("Creates new player record.")
    public PlayerDto create(@Valid @RequestBody PlayerDto body) {
        var entity = map(body, Player.class);
        return map(service.create(entity), PlayerDto.class);
    }

    @DeleteMapping(ID_PATH)
    @ApiOperation("Deletes single player record by ID.")
    public PlayerDto deleteById(@PathVariable Long id) {
        return map(service.delete(id), PlayerDto.class);
    }

    @PutMapping
    @ApiOperation("Updates single player record by ID.")
    public PlayerDto update(@PathVariable Long id, @Valid @RequestBody PlayerDto body) {
        var entity = map(body, Player.class);
        return map(service.update(id, entity), PlayerDto.class);
    }

}
