package org.lobsterZelda;

import org.lobsterZelda.utils.StaticCacheLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//Entrypoint for Java server.
@SpringBootApplication
@ComponentScan
public class MainApplication {
    public static void main(String[] args) {
        StaticCacheLoader.loadAllCaches();
        SpringApplication.run(MainApplication.class, args);
    }
}
