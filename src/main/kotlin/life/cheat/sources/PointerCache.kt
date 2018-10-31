package life.cheat.sources

import com.sun.jna.Pointer

object PointerCache {

    private val threadLocalPointer = ThreadLocal.withInitial { Pointer(0L) }
    private val pointer = Pointer(0L)

    fun pointerThreadSafe(address: Long): Pointer {
        val pointer = threadLocalPointer.get()
        Pointer.nativeValue(pointer, address)
        return pointer
    }

    fun pointer(address: Long) = pointer.apply {
        Pointer.nativeValue(this, address)
    }

}