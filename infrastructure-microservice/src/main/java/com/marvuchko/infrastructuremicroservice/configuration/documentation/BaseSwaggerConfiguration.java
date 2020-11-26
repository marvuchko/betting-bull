package com.marvuchko.infrastructuremicroservice.configuration.documentation;

import com.marvuchko.infrastructuremicroservice.resource.BaseResource;
import com.marvuchko.infrastructuremicroservice.resource.ServiceAwareResource;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Objects;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;
import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

public class BaseSwaggerConfiguration<P> {

    private static final String TITLE = "BettingBull REST API";
    private static final String DESCRIPTION = "Restful API for interacting with BettingBull platform";
    private static final String VERSION = "1.0.0";
    private static final String TERMS_OF_SERVICE_URL = "/v2/api-docs";
    private static final Contact CONTACT = new Contact("Marko Vuckovic", "", "marvuchko@gmail.com");
    private static final String LICENCE = "MIT";
    private static final String LICENCE_URL = "https://opensource.org/licenses/MIT";

    private final Class<P> resourcePackage;

    public BaseSwaggerConfiguration(Class<P> resourcePackage) {
        this.resourcePackage = resourcePackage;
    }

    @Bean
    public Docket api() {
        var tags = getTags();

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(basePackage(resourcePackage.getPackageName()))
                .paths(any())
                .build()
                .apiInfo(apiInfo())
                .tags(tags[0], tags);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                TITLE,
                DESCRIPTION,
                VERSION,
                TERMS_OF_SERVICE_URL,
                CONTACT,
                LICENCE,
                LICENCE_URL,
                emptySet()
        );
    }

    private Tag[] getTags() {

        var reflections = new Reflections(resourcePackage.getPackageName());

        var controllers = reflections
                .getTypesAnnotatedWith(RestController.class)
                .stream()
                .filter(controller -> ServiceAwareResource.class.equals(controller.getSuperclass()))
                .collect(toSet());

        return controllers
                .stream()
                .map(this::controllerToTag)
                .filter(Objects::nonNull).distinct()
                .toArray(Tag[]::new);

    }

    private Tag controllerToTag(Class<?> controller) {
        try {
            var tagNameField = controller.getDeclaredField(BaseResource.TAG_NAME_FIELD);
            var tagDescriptionField = controller.getDeclaredField(BaseResource.TAG_DESCRIPTION_FIELD);

            tagNameField.setAccessible(true);
            tagDescriptionField.setAccessible(true);

            var tagName = (String) tagNameField.get(controller);
            var tagDescription = (String) tagDescriptionField.get(controller);

            return new Tag(tagName, tagDescription);
        } catch (IllegalArgumentException | NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }

}
