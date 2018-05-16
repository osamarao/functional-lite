package com.nishtahir.function

/**
 * Allows you partially apply functions with
 * 2 arguments by returning a function with 1 arguments
 */
operator fun <P0, P1, R> Function2<P0, P1, R>.invoke(p0: P0) = { p1: P1 -> invoke(p0, p1) }

/**
 * Allows you partially apply functions with
 * 3 arguments by returning a function with 2 arguments
 */
operator fun <P0, P1, P2, R> Function3<P0, P1, P2, R>.invoke(p0: P0) = { p1: P1, p2: P2 -> invoke(p0, p1, p2) }
