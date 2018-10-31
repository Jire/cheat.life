package life.cheat.structure

abstract class StructureProperty(val structure: Structure, val size: Long) {

    var offset = 0L
        protected set

    inline fun pointer() = structure.pointer + offset

    abstract fun writeDefault()

}