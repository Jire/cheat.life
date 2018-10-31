package life.cheat.structure

import kotlin.reflect.KProperty

class LongProperty(structure: Structure, val defaultValue: Long) : StructureProperty(structure, 8) {

    override fun writeDefault() {
        set(defaultValue)
    }

    inline fun get() = unsafe.getLong(pointer())
    inline fun set(value: Long) = unsafe.putLong(pointer(), value)

    inline operator fun getValue(thisRef: Any?, property: KProperty<*>) = get()
    inline operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) = set(value)

    override fun toString() = get().toString()

}