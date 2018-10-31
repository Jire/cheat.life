package life.cheat.sources.windows

/**
 * Filter flags used for module listing.
 */
object FilterFlags {

    /**
     * List the 32-bit modules.
     */
    const val LIST_MODULES_32BIT = 0x01

    /**
     * List the 64-bit modules.
     */
    const val LIST_MODULES_64BIT = 0x02

    /**
     * List all modules.
     */
    const val LIST_MODULES_ALL = 0x03

    /**
     * Use the default behavior.
     */
    const val LIST_MODULES_DEFAULT = 0x0

}