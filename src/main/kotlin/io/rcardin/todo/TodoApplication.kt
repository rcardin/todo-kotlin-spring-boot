package io.rcardin.todo

import com.mongodb.reactivestreams.client.MongoClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication(exclude = [MongoReactiveDataAutoConfiguration::class])
@EnableReactiveMongoRepositories(basePackageClasses = [TaskRepository::class])
class TodoApplication {
	@Bean
	fun reactiveMongoTemplate(mongoClient: MongoClient): ReactiveMongoTemplate {
		return ReactiveMongoTemplate(mongoClient, "test")
	}
}

fun main(args: Array<String>) {
	runApplication<TodoApplication>(*args)
}
