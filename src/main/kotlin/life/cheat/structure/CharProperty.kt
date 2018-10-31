package life.cheat.structure

import kotlin.reflect.KProperty

class CharProperty(structure: Structure, val defaultValue: Char) : StructureProperty(structure, 4) {

    override fun writeDefault() {
        set(defaultValue)
    }

    inline fun get() = unsafe.getChar(pointer())
    inline fun set(value: Char) = unsafe.putChar(pointer(), value)

    inline operator fun getValue(thisRef: Any?, property: KProperty<*>) = get()
    inline operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Char) = set(value)

    override fun toString() = get().toString()

}