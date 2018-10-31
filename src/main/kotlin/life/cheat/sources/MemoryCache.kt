package life.cheat.sources

import com.sun.jna.Memory
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap

/**
 * Fast memory caching using a fast array map.
 *
 * Do not use results after they have been reused.
 */
object MemoryCache {

    /**
     * The maximum size of a memory in bytes to cache.
     */
    const val CACHE_BYTE_MAX = 128

    /**
     * The resource map cache, mapping size in bytes to memory.
     */
    private val map = Int2ObjectArrayMap<Memory>(64)

    private val empty = Memory(0)
    private val byte = Memory(1)
    private val short = Memory(2)
    private val int = Memory(4)
    private val long = Memory(8)

    /**
     * Returns a zeroed-out memory of the specified size in bytes.
     *
     * If the size meets the cached size limit, it will be reused.
     *
     * @param size The desired amount of bytes of the memory.
     * @param clear Whether or not to clear (zero-out) the returned memory. (By default this is `false`.)
     */
    operator fun get(size: Int, clear: Boolean = false): Memory {
        var memory = when (size) {
            0 -> empty
            1 -> byte
            2 -> short
            4 -> int
            8 -> long
            else -> map[size]
        }
        if (memory == null) {
            memory = Memory(size.toLong())
            if (size <= CACHE_BYTE_MAX) {
                map[size] = memory
            }
        } else if (clear) memory.clear()

        return memory
    }

}