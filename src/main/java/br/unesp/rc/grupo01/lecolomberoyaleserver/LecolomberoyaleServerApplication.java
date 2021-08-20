package br.unesp.rc.grupo01.lecolomberoyaleserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class LecolomberoyaleServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LecolomberoyaleServerApplication.class, args);
    }

    @Component
    public class InitializeData {

        @Autowired
        private DataSource dataSource;

        @EventListener(ApplicationReadyEvent.class)
        public void loadData() {
            ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false, false, "UTF-8", new ClassPathResource("data.sql"));
            resourceDatabasePopulator.execute(dataSource);
        }
    }
}
