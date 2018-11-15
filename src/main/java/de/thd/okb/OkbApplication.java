package de.thd.okb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.concurrent.Executor;

/**
 * Kicks off the application by entering the static main function.
 * SpringBootApplication does a lot of work behind the scenes.
 * <p>
 * Does some other config stuff.
 * Like async or the entity scan.
 *
 * @author tlang
 * @see <a href=
 * "http://docs.spring.io/autorepo/docs/spring-boot/current/reference/html/using-boot-using-springbootapplication-annotation.html"></a>
 */
@EntityScan(
        basePackageClasses = {OkbApplication.class}
)
@SpringBootApplication
@EnableAsync
public class OkbApplication extends AsyncConfigurerSupport {

    /**
     * Starts the current application
     *
     * @param args given commandline arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(OkbApplication.class, args);
    }

    /**
     * Hooks up the task executor for async stuff
     *
     * @return Executor
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(500);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }

    /**
     * Access to the jsr validator.
     *
     * @return javax.validation.Validator
     */
    @Bean
    public javax.validation.Validator localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }
}
