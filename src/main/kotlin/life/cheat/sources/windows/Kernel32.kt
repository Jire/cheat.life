package life.cheat.sources.windows

import com.sun.jna.Native
import com.sun.jna.NativeLibrary
import com.sun.jna.Pointer

/**
 * Provides zero-garbage [WriteProcessMemory] and [ReadProcessMemory] access.
 *
 * The "secret sauce" is avoiding JNA object allocations from translation-necessary types.
 */
object Kernel32 {

    /**
     * Writes memory to an area of memory in a specified process.
     * The entire area to be written to must be accessible or the operation fails.
     *
     * @param hProcess A handle to the process memory to be modified.
     * @param lpBaseAddress The base address in the specified process to which memory is written.
     * @param lpBuffer The buffer that contains memory to be written in the
     * address space of the specified process.
     * @param nSize The number of bytes to be written to the specified process.
     * @param lpNumberOfBytesWritten A variable that receives the number of bytes
     * transferred into the specified process.  If `null` the parameter is ignored.
     * @return `1` if successful, `0` otherwise.
     * To get extended error information, call [Native.getLastError()][com.sun.jna.Native#getLastError].
     */
    @JvmStatic
    external fun WriteProcessMemory(
        hProcess: Pointer, lpBaseAddress: Pointer, lpBuffer: Pointer,
        nSize: Int, lpNumberOfBytesWritten: Int
    ): Long

    /**
     * Reads memory from an area of memory in a specified process. The entire area
     * to be read must be accessible or the operation fails.
     *
     * @param hProcess
     *            A handle to the process with memory that is being read. The
     *            handle must have PROCESS_VM_READ access to the process.
     * @param lpBaseAddress
     *            A data to the base address in the specified process from
     *            which to read.
     *            Before any memory transfer occurs, the system verifies that all
     *            memory in the base address and memory of the specified size is
     *            accessible for read access, and if it is not accessible the
     *            function fails.
     * @param lpBuffer
     *            A data to a buffer that receives the contents from the
     *            address space of the specified process.
     * @param nSize
     *            The number of bytes to be read from the specified process.
     * @param lpNumberOfBytesRead
     *            A data to a variable that receives the number of bytes
     *            transferred into the specified buffer. If `lpNumberOfBytesRead`
     *            is `NULL`, the parameter is ignored.
     * @return If the function succeeds, the return value is nonzero.
     *         If the function fails, the return value is `0` (zero). To get
     *         extended error information, call [Native.getLastError()][com.sun.jna.Native#getLastError].
     *         The function fails if the requested read operation crosses into
     *         an area of the process that is inaccessible.
     */
    @JvmStatic
    external fun ReadProcessMemory(
        hProcess: Pointer, lpBaseAddress: Pointer, lpBuffer: Pointer,
        nSize: Int, lpNumberOfBytesRead: Int
    ): Long

    init {
        Native.register(NativeLibrary.getInstance("Kernel32"))
    }

}