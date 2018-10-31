package life.cheat.structure

import kotlin.reflect.KProperty

class DoubleProperty(structure: Structure, val defaultValue: Double) : StructureProperty(structure, 4) {

    override fun writeDefault() {
        set(defaultValue)
    }

    inline fun get() = unsafe.getDouble(pointer())
    inline fun set(value: Double) = unsafe.putDouble(pointer(), value)

    inline operator fun getValue(thisRef: Any?, property: KProperty<*>) = get()
    inline operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Double) = set(value)

    override fun toString() = get().toString()

}