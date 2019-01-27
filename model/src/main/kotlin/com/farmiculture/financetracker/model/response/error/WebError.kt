package com.farmiculture.financetracker.model.response.error

/**
 * Web Error which has the error details.
 * @author Jay
 * @since 1.0.0
 */
data class WebError(val message: String?, val details: List<WebErrorDetails>?, val stackTrace: Throwable?) {
}