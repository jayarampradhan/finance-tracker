package com.farmiculture.financetracker.js

import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.dom.create
import kotlinx.html.p
import org.w3c.dom.EventSource
import org.w3c.dom.events.Event

import kotlin.browser.document
import kotlin.browser.window


/**
 * external fun require(module: String): dynamic
 * require("path_to_file")
 *
 */
fun main(args: Array<String>) {
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
	window.addEventListener("scroll", onMs);
    window.onload = {
		val myDev = document.create.div("panel") {
			p {
				+"Here is a Test"
			}
		}
		document.getElementById("test")!!.append(myDev)
        val eventSource = EventSource("/api/users")
		var li = document.createElement("li").apply {
			innerHTML = getAnswer()
		}
		document.getElementById("users")!!.appendChild(li)
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
}

