package de.thd.okb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.Collections;

/**
 * Thymeleaf Configuration stuff.
 * The layout dialect should be back again.
 *
 * @author tlang
 */
@Configuration
public class ThymeleafConfiguration {

    /**
     * Bean for email templating (text mode)
     *
     * @return ClassLoaderTemplateResolver
     */
    @Bean
    public ClassLoaderTemplateResolver textTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setResolvablePatterns(Collections.singleton("text/*"));
        templateResolver.setPrefix("/mails/");
        templateResolver.setSuffix(".txt");
        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }
}
