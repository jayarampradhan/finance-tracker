package com.farmiculture.financetracker.ui.filter

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import java.io.IOException
import org.tuckey.web.filters.urlrewrite.Conf



/**
 * URL Rewrite filter.
 * @author Jay
 * @since 1.0.0
 */
const val URL_REWRITE_PATH = "classpath:/urlrewrite.xml"

@Component
class CustomUrlRewriteFilter: UrlRewriteFilter() {
	@Value(URL_REWRITE_PATH)
	lateinit var resource: Resource

	override fun loadUrlRewriter(filterConfig: FilterConfig?) {
		try {
			//Create a UrlRewrite Conf object with the injected resource
			val conf =
				Conf(filterConfig!!.getServletContext(), resource.inputStream, resource.filename, "@@farmi-cul-ft@@")
			checkConf(conf)
		} catch (ex: IOException) {
			throw ServletException("Unable to load URL rewrite configuration file from $URL_REWRITE_PATH", ex)
		}

	}
}