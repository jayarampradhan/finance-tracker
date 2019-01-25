package com.farmiculture.financetracker.model.request

/**
 * Specifies registration request.
 *
 * @author Jay
 * @since 1.0.0
 */
data class Registration (val firstName: String, val lastName: String, val userId: String) {
	var password: String = ""
}