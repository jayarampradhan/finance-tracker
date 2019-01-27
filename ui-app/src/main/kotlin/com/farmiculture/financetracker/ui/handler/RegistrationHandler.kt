package com.farmiculture.financetracker.ui.handler

import com.farmiculture.financetracker.model.ACCESS_TOKEN_HEADER
import com.farmiculture.financetracker.model.USER_ID_HEADER
import com.farmiculture.financetracker.model.User
import com.farmiculture.financetracker.model.request.Registration
import com.farmiculture.financetracker.model.response.CommonResponse
import com.farmiculture.financetracker.model.response.error.WebError
import com.farmiculture.financetracker.model.response.error.WebErrorDetails
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.util.*
import java.util.function.Consumer

/**
 * @author Jay
 * @since 1.0.0
 */
@Service
class RegistrationHandler{
	private val LOG: Logger = LoggerFactory.getLogger(RegistrationHandler::class.java.name)

	fun handle(rq: ServerRequest) : Mono<out ServerResponse> {
		return rq.bodyToMono(Registration::class.java).flatMap { m ->
			println(m.firstName)
			LOG.info("Tesing{}", m.firstName)
			when {
				m.firstName == "Test" -> {
					val token: String = UUID.randomUUID().toString()
					val user = User(id = 1L, firstName = m.firstName, lastName = m.lastName)
					val res: CommonResponse<User> = CommonResponse(data = user)
					val tokenCookie: ResponseCookie = ResponseCookie.from(ACCESS_TOKEN_HEADER, token)
						.path("/")
						.secure(true)
						.domain("*")
						.build()
					val userIdCookie: ResponseCookie = ResponseCookie.from(USER_ID_HEADER, user.id.toString())
						.path("/")
						.secure(true)
						.domain("*")
						.build()
					ServerResponse.status(HttpStatus.CREATED)
						.header(ACCESS_TOKEN_HEADER, token)
						.header(USER_ID_HEADER, user.id.toString())
						.cookie(tokenCookie)
						.cookie(userIdCookie)
						.body(BodyInserters.fromObject(res))
				}
				m.firstName == "TestValid" -> {
					val errorDetails = WebErrorDetails("firstName", "First Name is Invalid")
					val error = WebError(message = "Please correct the fields", details = listOf(errorDetails))
					val res: CommonResponse<WebError> = CommonResponse(data = error)
					ServerResponse.status(HttpStatus.BAD_REQUEST).body(BodyInserters.fromObject(res))
				}
				else -> {
					val errorDetails = WebErrorDetails("userName", "User Id is already taken")
					val error = WebError(message = "Please correct the fields", details = listOf(errorDetails))
					val res: CommonResponse<WebError> = CommonResponse(data = error)
					ServerResponse.status(HttpStatus.BAD_REQUEST).body(BodyInserters.fromObject(res))
				}
			}
		}
	}
}