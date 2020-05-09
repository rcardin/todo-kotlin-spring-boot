package io.rcardin.todo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class TaskRouters(private val handler: TaskHandler) {
    @Bean
    fun taskRouter() = coRouter {
        GET("/tasks", handler::findAllTasks)
        GET("tasks/{id}", handler::findOne)
    }
}