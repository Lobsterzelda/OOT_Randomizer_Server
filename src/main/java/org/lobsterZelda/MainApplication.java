package org.lobsterZelda;

import org.lobsterZelda.caches.staticData.JWTSecretKeyCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//Entrypoint for Java server.
@SpringBootApplication
@ComponentScan
public class MainApplication {
    public static void main(String[] args) {
        JWTSecretKeyCache.populateCache(); // Loads JWT Secrets into cache.
        SpringApplication.run(MainApplication.class, args);
    }
}
