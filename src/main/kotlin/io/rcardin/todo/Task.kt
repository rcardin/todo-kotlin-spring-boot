package io.rcardin.todo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

/**
 * A task to be performed
 */
@Document
data class Task(
        @Id val name: String,
        val description: String,
        val insertDate: LocalDateTime,
        val priority: Int
) {
    val state: TaskState = TaskState.TODO
}

enum class TaskState {
    TODO, IN_PROGRESS, DONE
}
