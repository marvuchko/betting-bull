package com.marvuchko.feeservice.configuration.documentation;

import com.marvuchko.feeservice.resource.ResourcePackage;
import com.marvuchko.infrastructuremicroservice.configuration.documentation.BaseSwaggerConfiguration;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ResourceDocumentation extends BaseSwaggerConfiguration<ResourcePackage> {

    public ResourceDocumentation() {
        super(ResourcePackage.class);
    }

}
