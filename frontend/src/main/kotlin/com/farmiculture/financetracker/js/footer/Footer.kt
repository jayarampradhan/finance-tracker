package com.farmiculture.financetracker.js.footer

import com.farmiculture.financetracker.js.common.getYearInStr
import kotlinx.html.*
import kotlinx.html.dom.create
import org.w3c.dom.HTMLElement
import kotlin.browser.document

/**
 * @author Jay
 * @since 1.0.0
 */


fun UL.footerLink(content: String, link: String, block : LI.() -> Unit) {
	li("mr-3") {
		a {
			href = link
			+content
			title = content
			attributes["aria-label"] = content
		}
		block()
	}
}

fun createFooter(): HTMLElement {
	return document.create.footer {
		div {
			classes = setOf("position-relative d-flex justify-content-between py-3 pb-2 my-3 text-gray border-top border-gray-light")
			id = "footerWrapper"
			ul {
				classes = setOf("list-style-none d-flex flex-wrap px-0")
				li ("mr-3"){
					+"© ".concat(getYearInStr()).concat(" ")
					span {
						title = "Farmiculture, changing way of living"
						+"Farmiculture, Community."
					}
				}
				footerLink("Terms", "/terms"){}
				footerLink("Privacy", "/privacy"){}
				footerLink("Security", "/security"){}
				footerLink("Help", "/help"){}
			}
		}
	}
}

