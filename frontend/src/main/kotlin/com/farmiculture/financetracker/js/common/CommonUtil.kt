package com.farmiculture.financetracker.js.common

import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSpanElement
import kotlin.browser.document

/**
 * basic utility for the common tasks.
 * @author Jay
 * @since 1.0.0
 */

fun getRequiredButtonById(id: String): HTMLButtonElement {
	return document.getElementById(id)!! as HTMLButtonElement;
}

fun getRequiredInputById(id: String): HTMLInputElement {
	return document.getElementById(id)!! as HTMLInputElement;
}

fun getRequiredDivById(id: String): HTMLDivElement {
	return document.getElementById(id)!! as HTMLDivElement;
}

fun getRequiredSpanById(id: String): HTMLSpanElement {
	return document.getElementById(id)!! as HTMLSpanElement;
}

fun setTextById(id: String, text: String) {
	document.getElementById(id)!!.textContent = text
}

fun isSuccess(code: Int): Boolean {
	return code in 200..299
}

