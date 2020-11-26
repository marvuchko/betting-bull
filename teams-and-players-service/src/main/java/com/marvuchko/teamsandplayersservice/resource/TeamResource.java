package com.marvuchko.teamsandplayersservice.resource;

import com.marvuchko.infrastructuremicroservice.resource.ServiceAwareResource;
import com.marvuchko.teamsandplayersservice.data.entity.Team;
import com.marvuchko.teamsandplayersservice.dto.TeamDto;
import com.marvuchko.teamsandplayersservice.service.TeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = TeamResource.TAG_NAME)
@RequestMapping(TeamResource.BASE_URL)
public class TeamResource extends ServiceAwareResource<TeamService> {

    public static final String BASE_URL = "/team";
    public static final String PLAYER_URL = ID_PATH + "/player/{playerId}";

    public static final String TAG_NAME = "Team Resource";
    public static final String TAG_DESCRIPTION = "Allows managing data about teams.";

    protected TeamResource(TeamService service) {
        super(service);
    }

    @GetMapping
    @ApiOperation("Returns information about teams, with pagination.")
    public Page<TeamDto> getPage(
           @PageableDefault(size = DEFAULT_PAGE_SIZE, sort = { ID_FIELD }, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return mapPage(service.getPage(pageable), TeamDto.class);
    }

    @GetMapping(ID_PATH)
    @ApiOperation("Returns single team record by ID.")
    public TeamDto getById(@PathVariable Long id) {
        return map(service.getById(id), TeamDto.class);
    }

    @PostMapping
    @ApiOperation("Creates new team record.")
    public TeamDto create(@Valid @RequestBody TeamDto body) {
        var entity = map(body, Team.class);
        return map(service.create(entity), TeamDto.class);
    }

    @PutMapping(ID_PATH)
    @ApiOperation("Updates single team record by ID.")
    public TeamDto update(@PathVariable Long id, @Valid @RequestBody TeamDto body) {
        var entity = map(body, Team.class);
        return map(service.update(id, entity), TeamDto.class);
    }

    @DeleteMapping(ID_PATH)
    @ApiOperation("Deletes single team record by ID.")
    public TeamDto deleteById(@PathVariable Long id) {
        return map(service.delete(id), TeamDto.class);
    }

    @PostMapping(PLAYER_URL)
    @ApiOperation("Add specific player to team.")
    public TeamDto addPlayerToTeam(@PathVariable Long id, @PathVariable Long playerId) {
        return map(service.addPlayerToTeam(id, playerId), TeamDto.class);
    }

    @DeleteMapping(PLAYER_URL)
    @ApiOperation("Remove specific player from team.")
    public TeamDto removeFromTeam(@PathVariable Long id, @PathVariable Long playerId) {
        return map(service.removeFromTeam(id, playerId), TeamDto.class);
    }

}
