package life.cheat.sources.windows

import com.sun.jna.Native
import com.sun.jna.platform.win32.Tlhelp32
import com.sun.jna.platform.win32.WinDef
import com.sun.jna.platform.win32.Kernel32.INSTANCE as JNAKernel32

/**
 * Utility functions for dealing with native processes on Windows.
 */
object Windows {

    /**
     * Reusable DWORD of value zero; not intended to be mutated.
     */
    val DWORD_ZERO = WinDef.DWORD(0)

    /**
     * Opens a native process on Windows by the specified process ID, given the specified access flags.
     *
     * @param processID The process ID of the process to open.
     * @param accessFlags The access permission flags given to the process.
     */
    fun openProcess(processID: Int, accessFlags: Int): WindowsProcess {
        val handle = JNAKernel32.OpenProcess(accessFlags, true, processID)
        return WindowsProcess(processID, handle)
    }

    /**
     * Opens a native process on Windows of the specified process name.
     *
     * @param processName The process name of the process to open.
     */
    fun openProcess(processName: String, accessFlags: Int): WindowsProcess? {
        val snapshot = JNAKernel32.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPALL, DWORD_ZERO)
        val entry = Tlhelp32.PROCESSENTRY32.ByReference() // we reuse the same entry during iteration
        try {
            while (JNAKernel32.Process32Next(snapshot, entry)) {
                val fileName = Native.toString(entry.szExeFile)
                if (processName == fileName) return openProcess(entry.th32ProcessID.toInt(), accessFlags)
            }
        } finally {
            JNAKernel32.CloseHandle(snapshot)
        }
        return null
    }

}