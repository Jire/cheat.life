package life.cheat.sources

import com.sun.jna.Pointer
import life.cheat.Addressed

interface Module : Source, Addressed {

    /**
     * The process of which this module belongs to.
     */
    val process: Process

    /**
     * The name of the module.
     */
    val name: String

    /**
     * The size of the module in bytes.
     */
    val size: Long

    override fun read(address: Pointer, data: Pointer, bytesToRead: Int) = process.read(address, data, bytesToRead)

    override fun read(address: Long, data: Pointer, bytesToRead: Int) = process.read(offset(address), data, bytesToRead)

    override fun write(address: Pointer, data: Pointer, bytesToWrite: Int) = process.write(address, data, bytesToWrite)

    override fun write(address: Long, data: Pointer, bytesToWrite: Int) =
        process.write(offset(address), data, bytesToWrite)

}