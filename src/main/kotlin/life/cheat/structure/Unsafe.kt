@file:JvmName("Unsafe")

package life.cheat.structure

import sun.misc.Unsafe

val unsafe by lazy(LazyThreadSafetyMode.NONE) {
    val field = Unsafe::class.java.getDeclaredField("theUnsafe")
    field.isAccessible = true
    field.get(null) as Unsafe
}