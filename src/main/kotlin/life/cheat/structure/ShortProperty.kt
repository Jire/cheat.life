package life.cheat.structure

import kotlin.reflect.KProperty

class ShortProperty(structure: Structure, val defaultValue: Short) : StructureProperty(structure, 2) {

    override fun writeDefault() {
        set(defaultValue)
    }

    inline fun get() = unsafe.getShort(pointer())
    inline fun set(value: Short) = unsafe.putShort(pointer(), value)

    inline operator fun getValue(thisRef: Any?, property: KProperty<*>) = get()
    inline operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Short) = set(value)

    override fun toString() = get().toString()

}