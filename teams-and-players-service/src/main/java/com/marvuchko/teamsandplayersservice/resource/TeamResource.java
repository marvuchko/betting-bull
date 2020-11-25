package com.marvuchko.teamsandplayersservice.resource;

import com.marvuchko.infrastructuremicroservice.resource.ServiceAwareResource;
import com.marvuchko.teamsandplayersservice.data.entity.Team;
import com.marvuchko.teamsandplayersservice.dto.TeamDto;
import com.marvuchko.teamsandplayersservice.service.TeamService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.util.Set;

@RestController
@RequestMapping(TeamResource.BASE_URL)
public class TeamResource extends ServiceAwareResource<TeamService> {

    public static final String BASE_URL = "/team";

    protected TeamResource(TeamService service) {
        super(service);
    }

    @GetMapping
    public Page<TeamDto> getPage(
           @PageableDefault(size = 20, sort = { ID_FIELD }, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return mapPage(service.getPage(pageable), TeamDto.class);
    }

    @GetMapping(ID_PATH)
    public TeamDto getById(@PathVariable Long id) {
        return map(service.getById(id), TeamDto.class);
    }

    @PostMapping
    public TeamDto create(@Valid @RequestBody TeamDto body) {
        var entity = map(body, Team.class);
        return map(service.create(entity), TeamDto.class);
    }

    @PutMapping(ID_PATH)
    public TeamDto update(@PathVariable Long id, @Valid @RequestBody TeamDto body) {
        var entity = map(body, Team.class);
        return map(service.update(id, entity), TeamDto.class);
    }

    @DeleteMapping
    public Set<TeamDto> deleteByIds(@QueryParam(IDS_QUERY_PARAM) Set<Long> ids) {
        return mapSet(service.deleteByIds(ids), TeamDto.class);
    }

    @DeleteMapping(ID_PATH)
    public TeamDto deleteById(@PathVariable Long id) {
        return map(service.delete(id), TeamDto.class);
    }

}
