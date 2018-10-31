@file:JvmName("Allocate")

package life.cheat.structure

operator fun <T : Structure> T.invoke(): StructureReference<T> {
    size = internalPointer

    pointer = unsafe.allocateMemory(size)

    if (defaultPointer == Structure.NIL) {
        defaultPointer = pointer

        for (property in properties) {
            property.writeDefault()
        }

        pointer = unsafe.allocateMemory(size) // need a new pointer cuz we just wrote the default shit.
    }

    unsafe.copyMemory(defaultPointer, pointer, size)

    return StructureReference(pointer)
}

inline operator fun <T : Structure, R> T.invoke(initializer: T.() -> R?): StructureReference<T> {
    val pointer = invoke()
    initializer()
    return pointer
}