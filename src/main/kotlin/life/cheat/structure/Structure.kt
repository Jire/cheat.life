package life.cheat.structure

abstract class Structure {

    companion object {
        const val NIL = -1L
        const val NO_SIZE = 0L
    }

    var internalPointer = 0L
    var defaultPointer = NIL
    var pointer = NIL

    var size = NO_SIZE

    val properties: MutableSet<StructureProperty> = HashSet()

    inline operator fun <T : Structure> get(reference: StructureReference<T>) = apply {
        pointer = reference.pointer
    }

    inline operator fun unaryMinus() = unsafe.freeMemory(pointer)

}