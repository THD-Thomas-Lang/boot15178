package de.thd.okb;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * ServletInitializer to start up as war file.
 *
 * @author tlang
 */
public class ServletInitializer extends SpringBootServletInitializer {

    /**
     * Adds the given application to the specified sources.
     *
     * @param application the given application
     * @return SpringApplicationBuilder
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OkbApplication.class);
    }

}
