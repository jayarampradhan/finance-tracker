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

	private var headers = mapOf<String, String>()
	//Time Out In milliseconds
	private var timeOut :Int = 1000
	private var completeHandler: ((O?, Event) -> Unit)? = null
	private var progressHandler: ((Event) -> Unit)? = null
	private var errorHandler: ((F?, Event) -> Unit)? = null
	private var exceptionHandler: ((Event) -> Unit)? = null
	private var abortHandler: ((Event) -> Unit)? = null
	private var timeOutHandler: ((Event) -> Unit)? = null
	private var finalizeHandler: ((Event) -> Unit)? = null
	private var _data: I? = null
	private var data: String? = null

	fun onComplete(action: (O?, Event) -> Unit): ApiCaller<I, O, F>{
		this.completeHandler = action
		return this
	}
	fun onProgress(action: (Event) -> Unit): ApiCaller<I, O, F>{
		this.progressHandler = action
		return this
	}
	fun onError(action: (F?, Event) -> Unit): ApiCaller<I, O, F> {
		this.errorHandler = action
		return this
	}
	fun onTimeOut(action: (Event) -> Unit): ApiCaller<I, O, F> {
		this.timeOutHandler = action
		return this
	}
	fun onException(action: (Event) -> Unit): ApiCaller<I, O, F> {
		this.exceptionHandler = action
		return this;
	}
	fun onRequestComplete(action: (Event) -> Unit): ApiCaller<I, O, F> {
		this.finalizeHandler = action
		return this;
	}
	fun withHeaders(headers: Map<String, String>): ApiCaller<I, O, F> {
		this.headers = HashMap(headers).toMap()
		return this
	}

	fun usingTimeOut(timeOut: Int = 1000): ApiCaller<I, O, F> {
		this.timeOut = timeOut
		return this
	}

	fun withBody(data: I): ApiCaller<I, O, F> {
		this._data = data
		return this
	}

	fun onAbort(action: (Event) -> Unit): ApiCaller<I, O, F> {
		this.abortHandler = action
		return this
	}

	private fun parseData() {
		if (this.headers["Content-Type"].equals("application/json", ignoreCase = true)) {
			this.data = JSON.stringify(_data)
		}
		//TODO code for the XML and form data
	}

	private fun parseResult(response: String): O? {
		if (this.headers["Accept"].equals("application/json", ignoreCase = true)) {
			return JSON.parse(response)
		}
		//TODO code for the XML and form data
		return null
	}

	private fun parseErrorResult(response: String): F? {
		if (this.headers["Accept"].equals("application/json", ignoreCase = true)) {
			return JSON.parse(response)
		}
		//TODO code for the XML and form data
		return null
	}

	private fun onRequestComplete(e: Event) {
		console.info("final request complete call")
		this.finalizeHandler?.invoke(e)
	}


	fun doCall() {
		val req = XMLHttpRequest()
		req.open(method.method, url, async)
		req.timeout = timeOut
		headers.forEach { (key, value) -> req.setRequestHeader(key, value) }
		parseData()
		if (data != null) {
			req.send(data)
		} else {
			req.send()
		}
		req.addEventListener("load", {
			console.info("Response"+req.responseText)
			console.info("Response ready state"+req.readyState)
			console.info("Response status"+req.status)
			if (req.status in 200 .. 299) {
				this.completeHandler?.invoke(parseResult(req.responseText), it)
			} else {
				this.errorHandler?.invoke(parseErrorResult(req.responseText), it)
			}
			onRequestComplete(it)
		})
		req.addEventListener("error", {
			console.info("EResponse"+it.eventPhase)
			console.info("EResponse ready state"+req.readyState)
			console.info("EResponse status"+req.status)
			this.exceptionHandler?.invoke(it)
			onRequestComplete(it)
		})
		req.addEventListener("timeout", {
			console.info("TResponse"+it.eventPhase)
			console.info("TResponse ready state"+req.readyState)
			console.info("TResponse status"+req.status)
			this.timeOutHandler?.invoke(it)
			onRequestComplete(it)
		})
		req.addEventListener("progress", {
			console.info("PResponse"+it.eventPhase)
			console.info("PResponse ready state"+req.readyState)
			console.info("PResponse status"+req.status)
			this.progressHandler?.invoke(it)
		})
		req.addEventListener("abort", {
			this.abortHandler?.invoke(it)
			onRequestComplete(it)
		})
	}

}