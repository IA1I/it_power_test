package com.example.test_task_it_power.migrations;

import com.example.test_task_it_power.IntegrationTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

@SpringBootTest
public class MigrationTest extends IntegrationTest {
    @Autowired
    private JdbcClient jdbcClient;

    @Test
    void shouldCheckThatAllTablesAreCreated() {
        List<Object> expected = List.of("databasechangelog", "databasechangeloglock", "tasks", "users");
        List<Object> actual = jdbcClient.sql("""
                        SELECT table_name 
                        FROM information_schema.tables
                        WHERE table_schema = 'public' AND table_catalog = current_database()
                        """)
                .query()
                .singleColumn();

        Assertions.assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}
