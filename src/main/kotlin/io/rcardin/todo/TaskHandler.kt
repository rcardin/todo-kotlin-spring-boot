package io.rcardin.todo

import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Flux
import java.net.URI

@Component
class TaskHandler(private val repository: TaskRepository) {
    suspend fun findAllTasks(request: ServerRequest): ServerResponse {
        val tasks: Flux<Task> = repository.findAll()
        return ServerResponse
                .ok()
                .json()
                .bodyAndAwait(tasks.asFlow())
    }

    suspend fun findOne(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id")
        val task = repository.findById(id).awaitFirstOrNull()
        return if (task != null) {
            ServerResponse
                    .ok()
                    .json()
                    .bodyValueAndAwait(task)
        } else {
            ServerResponse
                    .notFound()
                    .buildAndAwait()
        }
    }

    /*
    suspend fun save(request: ServerRequest): ServerResponse {
        val task = request.bodyToMono(Task::class.java)
                .flatMap { repository.save(it) }
                .map { it.name }
        return ServerResponse
                .created(URI.create("/tasks/"))
    }
     */
}