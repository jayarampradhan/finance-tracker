package com.farmiculture.financetracker.js

import com.farmiculture.financetracker.js.beforelogin.RegistrationView
import com.farmiculture.financetracker.js.common.require
import com.farmiculture.financetracker.js.footer.createFooter
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event

import kotlin.browser.document
import kotlin.browser.window

/**
 * Handling the index.html content.
 *
 * @author Jayaram Pradhan
 * @since 1.0.0
 */

fun main(args: Array<String>) {
	loadExternalLibs()
	document.getElementById("footerWrapperContainer")!!.append(createFooter())

	js("$('.toast').toast('show')")
	val onMs: (Event) -> dynamic= {
//		val distanceY = window.pageYOffset || document.documentElement.scrollTop;
		val shrinkOn = 300;
		val header = document.querySelector("header");
		print(header)
//		if (distanceY > shrinkOn) {
//			classie.add(header,"smaller");
//		} else {
//			if (classie.has(header,"smaller")) {
//				classie.remove(header,"smaller");
//			}
//		}
	}
//	window.addEventListener("scroll", onMs);
//    window.onload = {
//		val myDev = document.create.div("panel") {
//			p {
//				+"Here is a Test"
//			}
//		}

	val button = document.getElementById("login") as HTMLInputElement;

	button.addEventListener("click", {
		console.info("Actually Working")
		val firstName = (document.getElementById("firstName")!! as HTMLInputElement).value;
		console.info(firstName);

	})
	RegistrationView().handleRegistrationView()

//        val eventSource = EventSource("/api/users")
//		var li = document.createElement("li").apply {
//			innerHTML = getAnswer()
//		}
//		document.getElementById("users")!!.appendChild(li)
//        val onMessage: (Event) -> dynamic = {
//            // TODO Use it.data when KT-20743 will be fixed
////            val user = JSON.parse<Any>((it as MessageEvent).data as String)
//            var li = document.createElement("li").apply {
//                innerHTML = getAnswer()
//            }
//            document.getElementById("users")!!.appendChild(li)
//        }
        // TODO Use eventSource.onmessage when KT-20741 will be fixed
//        eventSource.addEventListener("message", onMessage)

}
fun loadExternalLibs() {
	require("bootstrap")
}

