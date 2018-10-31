package life.cheat.sources

import life.cheat.Addressed

inline class Pointer(override val address: Long) : Addressed {

    fun createConstant() = com.sun.jna.Pointer.createConstant(address)

}