package io.rcardin.todo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TaskRouters(private val handler: TaskHandler) {
    @Bean
    fun taskRouter(): Nothing = TODO()
}