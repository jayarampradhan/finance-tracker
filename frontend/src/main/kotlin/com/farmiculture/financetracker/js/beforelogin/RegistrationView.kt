package com.farmiculture.financetracker.js.beforelogin

import com.farmiculture.financetracker.js.common.*
import com.farmiculture.financetracker.js.common.ajax.ApiCaller
import com.farmiculture.financetracker.model.User
import com.farmiculture.financetracker.model.request.Registration
import com.farmiculture.financetracker.model.response.CommonResponse
import com.farmiculture.financetracker.model.response.error.WebError
import org.w3c.dom.asList
import kotlin.browser.document
import kotlin.browser.window
import kotlin.dom.addClass
import kotlin.dom.removeClass

/**
 * This handles the registration view and takes care about the server handlers.
 *
 * @author Jay
 * @since 1.0.0
 */
class RegistrationView {

	fun handleRegistrationView() {
		val signUp = getRequiredButtonById("signUp")
		window.onload = {
			getRequiredInputById("firstName").focus()
		}

		signUp.addEventListener(CLICK_EVENT, {
			signUp.setAttribute(DISABLED, "true")
			val firstName = getRequiredInputById("firstName")
			val lastName = getRequiredInputById("lastName")
			val userName = getRequiredInputById("userName")
			val password = getRequiredInputById("password")
			val regReq = Registration(firstName = firstName.value, lastName = lastName.value, userId = userName.value)
			regReq.password = password.value
			console.info(document.getElementById("signUpForm")?.getElementsByClassName(IS_INVALID_CSS))
			document.getElementById("signUpForm")?.getElementsByClassName(IS_INVALID_CSS)?.asList()?.forEach {
				elm ->
				console.info(elm)
				elm.removeClass(IS_INVALID_CSS)
			}
			handleRegistration(regReq)
		})

	}

	private fun handleRegistration(registration: Registration) {
		val signUpSpinner = getRequiredSpanById("signUpSpinner")
		val signUpErrorWrapper = getRequiredDivById("signUpErrorWrapper")
		ApiCaller<Registration, CommonResponse<User>, CommonResponse<WebError>>(RequestMethod.POST, "/signup/")
			.withHeaders(mapOf(CONTENT_TYPE to APPLICATION_JSON, ACCEPT to APPLICATION_JSON))
			.withBody(registration)
			.onProgress {
				signUpSpinner.removeClass(INVISIBLE_CSS)
				signUpErrorWrapper.addClass(INVISIBLE_CSS, POSITION_ABSOLUTE)
				setTextById("signUpErrorMessage", "")
			}
			.onRequestComplete {
				signUpSpinner.addClass(INVISIBLE_CSS)
				getRequiredButtonById("signUp").removeAttribute(DISABLED)
			}
			.onComplete { u,_ ->
				u?.let {
					if(isSuccess(u.code)){
						console.info("redirect user to the home" + u.data)
					}
				}
			}
			.onError { rs, _ ->  rs?.let{
				rs.data?.let {
					it.message?.let { s ->
						setTextById("signUpErrorMessage", s)
						signUpErrorWrapper.removeClass(INVISIBLE_CSS, POSITION_ABSOLUTE)
					}
				it.details?.let {
						eds -> val length = jsArrayLength(eds)
						for (i in 0..(length - 1)) {
							//TODO was limitation in kotlin js, hence fall back to that
							val elm = jsGetArrayElement(eds, i)
							val elmId = jsGetValueFromObject(elm, "id")
							getRequiredInputById(elmId).addClass(IS_INVALID_CSS)
						}
					}
				}
			}}
			.doCall()
	}

}
