package com.nishtahir.function

/**
 * @param T: Expected optional type
 */
sealed class Option<out T> {

    companion object {

        /**
         * Factory method to create a Some value
         * @param T type to wrap
         */
        fun <T> some(value: T) = Option.Some(value)

        /**
         * Factory method to create a None Value
         */
        fun none() = Option.None
    }

    /**
     * Performs a transformation on an Optional. The result itself is wrapped inside of an optional.
     * @param R expected return type
     * @param transform transformation to apply
     * @return Option<R>
     */
    inline fun <R> map(crossinline transform: (T) -> R?): Option<R> = fold({ None }, { a -> transform(a).asOptional() })

    /**
     * Performs a transformation on an Optional.
     * It's similar to the `map` operation except that the result is not wrapped inside of an optional.
     * The transformation is expected to produce it's own Optional result
     *
     * @param R Return type
     * @param transform transformation to apply
     */
    inline fun <R> flatMap(crossinline transform: (T) -> Option<R>): Option<R> = fold({ None }, { transform(it) })

    /**
     * Alias for Option#flatMap
     * @param R Return type
     * @param transform transformation to apply
     */
    inline fun <R> andThen(crossinline transform: (T) -> Option<R>): Option<R> = flatMap(transform)

    /**
     *
     */
    inline fun <R> fold(crossinline ifEmpty: () -> R, crossinline some: (T) -> R): R = when (this) {
        is None -> ifEmpty()
        is Some -> some(value)
    }

    /**
     * Maps the Option to its Kotlin nullable equivalent.
     */
    fun asNullable(): T? {
        return when (this) {
            is Some -> value
            is None -> null
        }
    }

    /**
     * Structure representing the availability of a value.
     * @param T type of value to be contained
     * @param value value to be wrapped in Some
     */
    data class Some<out T> internal constructor(val value: T) : Option<T>()

    /**
     * Structure representing the absence of a value
     */
    object None : Option<Nothing>()

}

/**
 * Wraps a nullable type to it's Optional equivalent
 * @param T type to wrap
 * @return Nullable type wrapped as an optional
 */
fun <T> T?.asOptional(): Option<T> = if (this == null) {
    Option.None
} else {
    Option.some(this)
}

/**
 * @param T
 * @param default
 * @return result of unwrapping the optional
 */
fun <T> Option<T>.getOrElse(default: T): T = fold({ default }, { it })


