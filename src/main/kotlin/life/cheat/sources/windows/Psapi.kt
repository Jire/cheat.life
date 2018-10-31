package life.cheat.sources.windows

import com.sun.jna.Native
import com.sun.jna.platform.win32.WinDef
import com.sun.jna.platform.win32.WinNT
import com.sun.jna.ptr.IntByReference
import com.sun.jna.win32.StdCallLibrary

interface Psapi : StdCallLibrary {

    fun EnumProcessModulesEx(
        hProcess: WinNT.HANDLE, lphModule: Array<WinDef.HMODULE?>, cb: Int,
        lpcbNeeded: IntByReference, dwFilterFlag: Int = FilterFlags.LIST_MODULES_ALL
    ): Boolean

    fun GetModuleBaseNameA(
        hProcess: WinNT.HANDLE, hModule: WinDef.HMODULE,
        lpBaseName: ByteArray, nSize: Int
    ): Int

    companion object {
        val INSTANCE: Psapi = Native.load("Psapi", Psapi::class.java)
    }

}