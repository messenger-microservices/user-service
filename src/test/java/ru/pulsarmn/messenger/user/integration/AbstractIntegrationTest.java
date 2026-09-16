package ru.pulsarmn.messenger.user.integration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractIntegrationTest {

    private static final String DEFAULT_USERNAME = "test";
    private static final String DEFAULT_PASSWORD = "test";

    public static final PostgreSQLContainer container = new PostgreSQLContainer(DockerImageName.parse("postgres:18"))
            .withUsername(DEFAULT_USERNAME)
            .withPassword(DEFAULT_PASSWORD);

    static {
        container.start();
    }

    @DynamicPropertySource
    static void postgresConfiguration(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
    }
}
