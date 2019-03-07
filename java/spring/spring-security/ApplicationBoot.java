package com.oryam.howto.application.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import com.oryam.howto.application.config.AppCommonConfiguration;
import com.oryam.howto.application.config.AppDomainConfiguration;
import com.oryam.howto.application.config.AppPersistenceConfiguration;
import com.oryam.howto.application.config.AppWebConfiguration;
import com.oryam.howto.application.config.CorsConfiguration;
import com.oryam.howto.application.config.OracleConfiguration;
import com.oryam.howto.application.config.WebSecurityConfiguration;

@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({
          CorsConfiguration.class,
          WebSecurityConfiguration.class,
})
public class ApplicationBoot {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBoot.class, args);
    }

}
