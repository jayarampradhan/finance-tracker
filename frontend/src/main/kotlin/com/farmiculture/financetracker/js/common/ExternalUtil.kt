package com.farmiculture.financetracker.js.common

/**
 * This will have kotlin/javascript functions.
 * @author Jay
 * @since 1.0.0
 */

external fun require(module: String): dynamic
fun jsTypeOf(o: Any): String {
	return js("typeof o")
}

fun jsArrayLength(o: Any): Int {
	return js("o.length;")
}

fun jsGetArrayElement(o: Any, index: Int): Any {
	return js("o[index];")
}

fun jsGetValueFromObject(o: Any, k: Any): String {
	return js("o[k];")
}