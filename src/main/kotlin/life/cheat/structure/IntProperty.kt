package life.cheat.structure

import kotlin.reflect.KProperty

class IntProperty(structure: Structure, val defaultValue: Int) : StructureProperty(structure, 4) {

    init {
        offset = structure.internalPointer
        structure.internalPointer += size
        structure.properties.add(this)
    }

    override fun writeDefault() = set(defaultValue)

    inline fun get() = unsafe.getInt(pointer())
    inline fun set(value: Int) = unsafe.putInt(pointer(), value)

    inline operator fun getValue(thisRef: Any?, property: KProperty<*>) = get()
    inline operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) = set(value)

    override fun toString() = get().toString()

}