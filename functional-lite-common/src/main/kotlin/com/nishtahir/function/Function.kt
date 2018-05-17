package com.nishtahir.function

inline fun <A, B, C> ((A) -> B).andThen(crossinline transform: (B) -> C): (A) -> C = { a -> transform(this(a)) }

inline fun <A, B, C> compose(crossinline func1: (A) -> B, crossinline func2: (C) -> A): (C) -> B = { func1(func2(it)) }
