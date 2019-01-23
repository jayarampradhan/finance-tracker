package com.farmiculture.financetracker.js.beforelogin

import com.farmiculture.financetracker.js.common.*
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLInputElement
import kotlin.browser.document
import kotlin.dom.addClass
import kotlin.dom.appendText
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
		val signUpErrorWrapper = getRequiredDivById("signUpErrorWrapper");
		signUpErrorWrapper.addClass(INVISIBLE_CLS, POSITION_ABSOLUTE)
		signUp.addEventListener("click", {
			signUp.setAttribute(DISABLED, "true")
			val firstName = getRequiredInputById("firstName")
			val lastName = getRequiredInputById("lastName")
			val userName = getRequiredInputById("userName")
			val password = getRequiredInputById("password")
			//TODO Actually Call for the registration and wait till it completes.
			//TODO if success redirect to the home/main screen, else show the error with the registration link

			setTextById("signUpErrorMessage", "Something Went Wrong")
			signUpErrorWrapper.removeClass(INVISIBLE_CLS, POSITION_ABSOLUTE)
			signUp.removeAttribute(DISABLED)
		})

	}
}
