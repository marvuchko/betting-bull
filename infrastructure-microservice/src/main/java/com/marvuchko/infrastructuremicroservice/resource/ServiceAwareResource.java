package com.marvuchko.infrastructuremicroservice.resource;

import com.marvuchko.infrastructuremicroservice.service.RepositoryAwareService;

public abstract class ServiceAwareResource<S extends RepositoryAwareService> extends BaseResource {

    protected final S service;

    protected ServiceAwareResource(S service) {
        this.service = service;
    }
}
