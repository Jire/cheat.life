package life.cheat

/**
 * Something that has an address and whose address can be used as a base to offset another.
 */
interface Addressed {

    /**
     * The address.
     */
    val address: Long

    /**
     * Offsets the base address by the specified offset.
     *
     * @param offset The offset in bytes off the base address.
     */
    fun offset(offset: Long) = address + offset

    /**
     * Offsets the base address by the specified offset.
     *
     * @param offset The offset in bytes off the base address.
     */
    fun offset(offset: Int) = offset(offset.toLong())

}