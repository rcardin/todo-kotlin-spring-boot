package io.rcardin.todo

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface TaskRepository : ReactiveCrudRepository<Task, String>

@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = [TaskRepository::class])
class ReactiveMongoConfig : AbstractReactiveMongoConfiguration() {
    override fun reactiveMongoClient(): MongoClient = mongoClient()

    override fun getDatabaseName(): String = "mongo"

    @Bean
    override fun reactiveMongoTemplate(): ReactiveMongoOperations {
        return ReactiveMongoTemplate(mongoClient(), databaseName)
    }

    @Bean
    fun mongoClient(): MongoClient = MongoClients.create()
}
