package life.cheat.structure

import kotlin.reflect.KProperty

class ByteProperty(structure: Structure, val defaultValue: Byte) : StructureProperty(structure, 1) {

    override fun writeDefault() {
        set(defaultValue)
    }

    inline fun get() = unsafe.getByte(pointer())
    inline fun set(value: Byte) = unsafe.putByte(pointer(), value)

    inline operator fun getValue(thisRef: Any?, property: KProperty<*>) = get()
    inline operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Byte) = set(value)

    override fun toString() = get().toString()

}