package com.farmiculture.financetracker.ui

import com.farmiculture.financetracker.model.request.Registration
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Flux
import java.net.URI
import java.time.Duration

/**
 * UIApplication entry point.
 * @author Jayaram Pradhan
 */
@SpringBootApplication
class UIApplication {
	@Bean
	fun routes() = router {
		GET("/") { ServerResponse.permanentRedirect(URI("/index.html")).build() }
		(POST("/signup") and accept(MediaType.APPLICATION_JSON)){
			val str = Registration(firstName = "", lastName = "", userId = "")
			ServerResponse.ok().build()
		}
		(GET("/api/users") and accept(MediaType.TEXT_EVENT_STREAM)) {
			val users = Flux.just(
				"Test", "Test2")

			val userStream = Flux
				.zip(Flux.interval(Duration.ofMillis(100)), users.repeat())
				.map { it.t2 }

			ServerResponse.ok().bodyToServerSentEvents(userStream)
		}
	}
}

fun main(args: Array<String>) {
	SpringApplication.run(UIApplication::class.java, *args)
}
