package io.rcardin.todo

import kotlinx.coroutines.reactive.asFlow
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait
import reactor.core.publisher.Flux

@Component
class TaskHandler(private val repository: TaskRepository) {
    suspend fun findAllTasks(): ServerResponse {
        val tasks: Flux<Task> = repository.findAll()
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .bodyAndAwait(tasks.asFlow());
    }
}