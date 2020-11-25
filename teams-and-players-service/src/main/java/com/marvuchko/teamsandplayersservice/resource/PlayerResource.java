package com.marvuchko.teamsandplayersservice.resource;

import com.marvuchko.infrastructuremicroservice.resource.ServiceAwareResource;
import com.marvuchko.teamsandplayersservice.data.entity.Player;
import com.marvuchko.teamsandplayersservice.dto.PlayerDto;
import com.marvuchko.teamsandplayersservice.service.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.util.Set;

@RestController
@RequestMapping(PlayerResource.BASE_URL)
public class PlayerResource extends ServiceAwareResource<PlayerService> {

    public static final String BASE_URL = "/player";

    protected PlayerResource(PlayerService service) {
        super(service);
    }

    @GetMapping
    public Page<PlayerDto> getPage(
            @PageableDefault(size = 20, sort = { ID_FIELD }, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return mapPage(service.getPage(pageable), PlayerDto.class);
    }

    @GetMapping(ID_PATH)
    public PlayerDto getById(@PathVariable Long id) {
        return map(service.getById(id), PlayerDto.class);
    }

    @PostMapping
    public PlayerDto create(@Valid @RequestBody PlayerDto body) {
        var entity = map(body, Player.class);
        return map(service.create(entity), PlayerDto.class);
    }

    @DeleteMapping
    public Set<PlayerDto> deleteByIds(@QueryParam(IDS_QUERY_PARAM) Set<Long> ids) {
        return mapSet(service.deleteByIds(ids), PlayerDto.class);
    }

    @DeleteMapping(ID_PATH)
    public PlayerDto deleteById(@PathVariable Long id) {
        return map(service.delete(id), PlayerDto.class);
    }

    @PutMapping
    public PlayerDto update(@PathVariable Long id, @Valid @RequestBody PlayerDto body) {
        var entity = map(body, Player.class);
        return map(service.update(id, entity), PlayerDto.class);
    }

}
