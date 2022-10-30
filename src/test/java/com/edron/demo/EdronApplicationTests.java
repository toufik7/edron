package com.edron.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EdronApplicationTests {

    private Logger logger = LoggerFactory.getLogger(EdronApplicationTests.class);

    @Test
    void contextLoads() {
        logger.info("Running TestApplicationTests: contextLoads()");
    }

}
