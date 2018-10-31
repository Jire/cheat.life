package life.cheat.structure

import kotlin.reflect.KProperty

class FloatProperty(structure: Structure, val defaultValue: Float) : StructureProperty(structure, 4) {

    override fun writeDefault() {
        set(defaultValue)
    }

    inline fun get() = unsafe.getFloat(pointer())
    inline fun set(value: Float) = unsafe.putFloat(pointer(), value)

    inline operator fun getValue(thisRef: Any?, property: KProperty<*>) = get()
    inline operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Float) = set(value)

    override fun toString() = get().toString()

}