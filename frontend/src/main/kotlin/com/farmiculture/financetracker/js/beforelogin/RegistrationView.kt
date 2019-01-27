package com.farmiculture.financetracker.js.beforelogin

import com.farmiculture.financetracker.js.common.*
import com.farmiculture.financetracker.js.common.ajax.ApiCaller
import com.farmiculture.financetracker.model.request.Registration
import com.farmiculture.financetracker.model.response.CommonResponse
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLTextAreaElement
import org.w3c.dom.events.Event
import org.w3c.xhr.XMLHttpRequest
import kotlin.browser.document
import kotlin.dom.addClass
import kotlin.dom.appendText
import kotlin.dom.removeClass
import kotlin.js.Json

/**
 * This handles the registration view and takes care about the server handlers.
 *
 * @author Jay
 * @since 1.0.0
 */
class RegistrationView {

	fun handleRegistrationView() {
		val signUp = getRequiredButtonById("signUp")
		val signUpErrorWrapper = getRequiredDivById("signUpErrorWrapper");
		signUpErrorWrapper.addClass(INVISIBLE_CLS, POSITION_ABSOLUTE)
		signUp.addEventListener(CLICK_EVENT, {
			console.info("going to Calling")
			signUp.setAttribute(DISABLED, "true")
			val firstName = getRequiredInputById("firstName")
			val lastName = getRequiredInputById("lastName")
			val userName = getRequiredInputById("userName")
			val password = getRequiredInputById("password")
			val regReq = Registration(firstName = firstName.value, lastName = lastName.value, userId = userName.value)
			regReq.password = password.value
			//TODO Actually Call for the registration and wait till it completes.
			//TODO if success redirect to the home/main screen, else show the error with the registration link
			console.info("going to Calling")
			handleRegistration(regReq)
			setTextById("signUpErrorMessage", "Something Went Wrong")
			signUpErrorWrapper.removeClass(INVISIBLE_CLS, POSITION_ABSOLUTE)
			signUp.removeAttribute(DISABLED)
		})

	}

	fun handleRegistration(registration: Registration) {
//		val caller1 = ApiCaller<CommonResponse<Unit>, Registration>(RequestMethod.POST, "/signup/")
//		val caller = ApiCaller<Registration, Registration, Any>(RequestMethod.POST, "/signup/")
//			.onComplete(action = )
//			.
//		caller.headers = mapOf("Content-Type" to "application/json")
//		caller.doCall(registration)


//		console.info("Calling")
//		val url = "/signup/"
//		val req = XMLHttpRequest()
//
//		req.onloadend = fun(event: Event){
//			console.info(req.response)
//			val text = req.responseText
//			console.info("Calling response")
//			println(text)
//			val objArray  = JSON.parse<Registration>(text)
//			println(objArray)
////			val textarea = document.getElementById("textarea_id") as HTMLTextAreaElement
////			textarea.value = ""
////			objArray.forEach {
////				val message = it["message"]
////				textarea.value += "$message\n"
////			}
//		}
	}
}
