package life.cheat.structure

fun Structure.byte(defaultValue: Byte = 0) = ByteProperty(this, defaultValue)
fun Structure.short(defaultValue: Short = 0) = ShortProperty(this, defaultValue)
fun Structure.int(defaultValue: Int = 0) = IntProperty(this, defaultValue)
fun Structure.long(defaultValue: Long = 0) = LongProperty(this, defaultValue)

fun Structure.char(defaultValue: Char = ' ') = CharProperty(this, defaultValue)

fun Structure.float(defaultValue: Float = 0F) = FloatProperty(this, defaultValue)
fun Structure.double(defaultValue: Double = 0.0) = DoubleProperty(this, defaultValue)

fun Structure.boolean(defaultValue: Boolean = false) = BooleanProperty(this, defaultValue)