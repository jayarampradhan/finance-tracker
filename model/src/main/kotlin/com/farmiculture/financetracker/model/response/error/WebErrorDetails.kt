package com.farmiculture.financetracker.model.response.error

/**
 * Contains the error details of hthe response field wise.
 * @author Jay
 * @since 1.0.0
 */
data class WebErrorDetails(val id: String, val message: String, val stackTrace: Throwable?) {

}