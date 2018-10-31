package life.cheat.structure

inline class StructureReference<T : Structure>(val pointer: Long) {

    // read

    inline fun byte(strukture: T) = unsafe.getByte(strukture.pointer + pointer)
    inline fun short(strukture: T) = unsafe.getShort(strukture.pointer + pointer)
    inline fun int(strukture: T) = unsafe.getInt(strukture.pointer + pointer)
    inline fun long(strukture: T) = unsafe.getLong(strukture.pointer + pointer)

    inline fun float(strukture: T) = unsafe.getFloat(strukture.pointer + pointer)
    inline fun double(strukture: T) = unsafe.getDouble(strukture.pointer + pointer)

    inline fun char(strukture: T) = unsafe.getChar(strukture.pointer + pointer)

    inline fun boolean(strukture: T) = byte(strukture) > 0

    // write

    inline fun setByte(strukture: T, value: Byte) = unsafe.putByte(strukture.pointer + pointer, value)
    inline fun setShort(strukture: T, value: Short) = unsafe.putShort(strukture.pointer + pointer, value)
    inline fun setInt(strukture: T, value: Int) = unsafe.putInt(strukture.pointer + pointer, value)
    inline fun setLong(strukture: T, value: Long) = unsafe.putLong(strukture.pointer + pointer, value)

    inline fun setFloat(strukture: T, value: Float) = unsafe.putFloat(strukture.pointer + pointer, value)
    inline fun setDouble(strukture: T, value: Double) = unsafe.putDouble(strukture.pointer + pointer, value)

    inline fun setChar(strukture: T, value: Char) = unsafe.putChar(strukture.pointer + pointer, value)

    inline fun setBoolean(strukture: T, value: Boolean) = setByte(strukture, if (value) 1 else 0)

}