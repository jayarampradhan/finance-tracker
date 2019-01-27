package com.farmiculture.financetracker.model.response

/**
 * Specifies the common response from the request/method calls.
 *
 * @author Jay
 * @since 1.0.0
 */
data class CommonResponse<out T>(val code: Int = 200, val data: T?) {

	fun isSuccess() : Boolean {
		return this.code in 200..299
	}
}