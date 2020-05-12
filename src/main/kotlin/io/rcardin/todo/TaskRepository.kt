package io.rcardin.todo

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface TaskRepository : ReactiveCrudRepository<Task, String>
