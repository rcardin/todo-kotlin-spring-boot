package io.rcardin.todo

import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Flux
import reactor.kotlin.core.publisher.switchIfEmpty
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
        return repository.findById(id)
                .flatMap { task: Task -> ServerResponse.ok().json().body<Task>(task) }
                .switchIfEmpty { ServerResponse.notFound().build() }
                .awaitSingle()
    }

    suspend fun save(request: ServerRequest): ServerResponse {
        return request.bodyToMono(Task::class.java)
                .flatMap { repository.save(it) }
                .map { it.name }
                .flatMap { name: String -> ServerResponse.created(URI("/tasks/$name")).build() }
                .awaitSingle()
    }
}