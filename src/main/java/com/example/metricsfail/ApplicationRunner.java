package com.example.metricsfail;

import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private static final Logger LOGGER = Logger.getLogger(ApplicationRunner.class);

    @Override
    public void run(final String... args) throws Exception {
        LOGGER.info("Running application");
    }
}
