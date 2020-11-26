package com.marvuchko.teamsandplayersservice.configuration.documentation;

import com.marvuchko.infrastructuremicroservice.configuration.documentation.BaseSwaggerConfiguration;
import com.marvuchko.teamsandplayersservice.resource.ResourcePackage;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ResourceDocumentation extends BaseSwaggerConfiguration<ResourcePackage> {

    public ResourceDocumentation() {
        super(ResourcePackage.class);
    }

}
