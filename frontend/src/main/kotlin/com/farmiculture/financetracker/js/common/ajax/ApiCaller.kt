package com.farmiculture.financetracker.js.common.ajax

import com.farmiculture.financetracker.js.common.RequestMethod
import org.w3c.dom.events.Event
import org.w3c.xhr.XMLHttpRequest

/**
 * A builder for calling different api.
 * @author Jay
 * @since 1.0.0
 */
data class ApiCaller<in I, out O, out F>(val method: RequestMethod = RequestMethod.GET, val url: String, val async: Boolean = true) {

	var headers = mapOf<String, String>()
	//Time Out In milliseconds
	var timeOut = 1000


	fun doCall(data: I?, onSuccess: (responseData: O, e: Event) -> Unit, onFailure: (responseData: F, e: Event) -> Unit) {
		val req = XMLHttpRequest()
		req.open(method.method, url, async)
		req.timeout = timeOut
		headers.forEach { (key, value) -> req.setRequestHeader(key, value) }
		console.info("Calling")
		if (data != null) {
			req.send(JSON.stringify(data))
		} else {
			req.send()
		}
		req.addEventListener("load", {
			console.info("Response"+req.responseText)
			console.info("Response ready state"+req.readyState)
			console.info("Response status"+req.status)
			if (req.status in 200 .. 299) {
				onSuccess(JSON.parse(req.responseText), it)
			} else {
				onFailure(JSON.parse(req.responseText), it)
			}
		})
		req.addEventListener("error", {
			console.info("EResponse"+it.eventPhase)
			console.info("EResponse ready state"+req.readyState)
			console.info("EResponse status"+req.status)
//			console.info(it.target.)
		})
		req.addEventListener("timeout", {
			console.info("TResponse"+it.eventPhase)
			console.info("TResponse ready state"+req.readyState)
			console.info("TResponse status"+req.status)
//			console.info(it.target.)
		})
		req.addEventListener("progress", {
			console.info("PResponse"+it.eventPhase)
			console.info("PResponse ready state"+req.readyState)
			console.info("PResponse status"+req.status)
//			console.info(it.target.)
		})
		req.addEventListener("abort", {
			console.info("AResponse"+it.eventPhase)
			console.info("AResponse ready state"+req.readyState)
			console.info("AResponse status"+req.status)
//			console.info(it.target.)
		})
	}

}