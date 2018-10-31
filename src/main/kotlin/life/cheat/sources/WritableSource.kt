package life.cheat.sources

import com.sun.jna.Pointer

interface WritableSource {

    fun write(address: Pointer, data: Pointer, bytesToWrite: Int): Boolean

    fun write(address: Long, data: Pointer, bytesToWrite: Int) =
        write(PointerCache.pointer(address), data, bytesToWrite)

    operator fun set(address: Long, value: Byte) = write(address, 1) {
        setByte(0, value)
    }

    operator fun set(address: Long, value: Short) = write(address, 2) {
        setShort(0, value)
    }

    operator fun set(address: Long, value: Int) = write(address, 4) {
        setInt(0, value)
    }

    operator fun set(address: Long, value: Long) = write(address, 8) {
        setLong(0, value)
    }

    operator fun set(address: Long, value: Float) = write(address, 4) {
        setFloat(0, value)
    }

    operator fun set(address: Long, value: Double) = write(address, 8) {
        setDouble(0, value)
    }

    operator fun set(address: Long, value: Char) = write(address, 2) {
        setChar(0, value)
    }

    operator fun set(address: Long, value: Boolean) = set(address, if (value) 1 else 0) // int

}

private inline fun WritableSource.write(address: Long, bytes: Int, writeBody: Pointer.() -> Unit) {
    val memory = MemoryCache[bytes]
    memory.writeBody()
    write(address, memory, bytes)
}