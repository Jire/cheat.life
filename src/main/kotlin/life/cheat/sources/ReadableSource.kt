package life.cheat.sources

import com.sun.jna.Pointer

interface ReadableSource {

    fun read(address: Pointer, data: Pointer, bytesToRead: Int): Boolean

    fun read(address: Long, data: Pointer, bytesToRead: Int) = read(PointerCache.pointer(address), data, bytesToRead)

}