internal const val DEFAULT_INITIAL_CAPACITY = 10

fun <P0, R> Function1<P0, R>.memoize(initialCapacity: Int = DEFAULT_INITIAL_CAPACITY): Function1<P0, R> {
    val cache = HashMap<P0, R>()
    return { p0 -> cache.getOrPut(p0, { this(p0) }) }
}

fun <P0, P1, R> Function2<P0, P1, R>.memoize(initialCapacity: Int = DEFAULT_INITIAL_CAPACITY): Function2<P0, P1, R> {
    val cache = HashMap<Pair<P0, P1>, R>()
    return { p0, p1 -> cache.getOrPut(p0 to p1, { this(p0, p1) }) }
}

fun <P0, P1, P2, R> Function3<P0, P1, P2, R>.memoize(initialCapacity: Int = DEFAULT_INITIAL_CAPACITY): Function3<P0, P1, P2, R> {
    val cache = HashMap<Triple<P0, P1, P2>, R>()
    return { p0, p1, p2 -> cache.getOrPut(Triple(p0, p1, p2), { this(p0, p1, p2) }) }
}

fun <P0, R> memoize(initialCapacity: Int = DEFAULT_INITIAL_CAPACITY, f: Function1<P0, R>): Function1<P0, R> {
    return f.memoize(initialCapacity)
}

fun <P0, P1, R> memoize(initialCapacity: Int = DEFAULT_INITIAL_CAPACITY, f: Function2<P0, P1, R>): Function2<P0, P1, R> {
    return f.memoize(initialCapacity)
}


fun <P0, P1, P2, R> memoize(initialCapacity: Int = DEFAULT_INITIAL_CAPACITY, f: Function3<P0, P1, P2, R>): Function3<P0, P1, P2, R> {
    return f.memoize(initialCapacity)
}
