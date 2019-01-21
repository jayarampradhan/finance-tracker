package com.farmiculture.financetracker.js.common

import kotlin.js.Date

/**
 * @author Jay
 * @since 1.0.0
 */
fun getYearInStr(): String {
	val d = Date();
	return d.getFullYear().toString();
}