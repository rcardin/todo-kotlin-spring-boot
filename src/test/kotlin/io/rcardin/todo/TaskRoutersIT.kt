package io.rcardin.todo

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = [TodoApplication::class]
)
internal class TaskRoutersIT(
        @Autowired val client: WebTestClient,
        @Autowired val repository: TaskRepository) {

    @Test
    fun `A user should get all the persisted tasks`() {
        val task = Task("task1", "A description", LocalDateTime.now(), 42)
        repository.save(task)
        client.get().uri("/tasks").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
    }
}