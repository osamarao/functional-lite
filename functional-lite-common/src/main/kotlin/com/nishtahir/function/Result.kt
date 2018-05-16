package com.nishtahir.function

sealed class Result<out T, out E> {

    companion object {

        /**
         * @param V Type of value to be contained in the result
         * @param value Value contained in the result
         * @return Result with the wrapped value
         */
        fun <V> ok(value: V): Result.Ok<V> = Result.Ok(value)

        /**
         * @param E Type of error to be contained in the result
         * @param error Error to be contained in the result
         * @return Result with the wrapped value
         */
        fun <E> error(error: E): Result.Error<E> = Result.Error(error)
    }

    /**
     * @param transform transformation to apply to the result
     * @return result with the map applied
     */
    inline fun <R> map(crossinline transform: (T) -> R): Result<R, E> = when (this) {
        is Ok -> ok(transform(value))
        is Error -> this
    }

    /**
     * @param ok transformation to apply if the result is Result.Ok
     * @param err transformation to apply if the result is Result.Error
     * @return result of the transformation applied
     */
    inline fun <R> fold(crossinline ok: (T) -> R, crossinline err: (E) -> R): R = when (this) {
        is Error -> err(error)
        is Ok -> ok(value)
    }

    /**
     *
     *  @param value value to wrap in the result
     */
    data class Ok<out T> internal constructor(val value: T) : Result<T, Nothing>()

    /**
     *  @param E Error type contained in the result
     *  @param error error to wrap in the result
     */
    data class Error<out E> internal constructor(val error: E) : Result<Nothing, E>()
}

/**
 *  @param T
 *  @param R
 *  @param E
 *  @param transform transformation to apply on the Result
 *  @return Result with the transformation applied
 */
inline fun <T, R, E> Result<T, E>.flatMap(crossinline transform: (T) -> Result<R, E>): Result<R, E> {
    return fold({ transform(it) }, { Result.error(it) })
}

/**
 *  @param default
 */
fun <T, E> Result<T, E>.getOrElse(default: T): T = (this as? Result.Ok)?.value ?: default


