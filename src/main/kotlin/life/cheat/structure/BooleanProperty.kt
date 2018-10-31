package life.cheat.structure

import kotlin.reflect.KProperty

class BooleanProperty(structure: Structure, val defaultValue: Boolean) : StructureProperty(structure, 1) {

    override fun writeDefault() {
        set(defaultValue)
    }

    inline fun get() = unsafe.getByte(pointer()) > 0
    inline fun set(value: Boolean) = unsafe.putByte(pointer(), if (value) 1 else 0)

    inline operator fun getValue(thisRef: Any?, property: KProperty<*>) = get()
    inline operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) = set(value)

    override fun toString() = get().toString()

}