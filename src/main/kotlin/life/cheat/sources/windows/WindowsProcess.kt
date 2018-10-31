package life.cheat.sources.windows

import com.sun.jna.Native
import com.sun.jna.Pointer as JNAPointer
import com.sun.jna.platform.win32.WinDef
import com.sun.jna.platform.win32.WinNT
import com.sun.jna.ptr.IntByReference
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap
import java.util.Collections
import life.cheat.sources.Process
import com.sun.jna.platform.win32.Psapi.INSTANCE as JNAPsapi
import life.cheat.sources.windows.Psapi.Companion.INSTANCE as Psapi

/**
 * Represents a process on Windows.
 */
class WindowsProcess(override val id: Int, val handle: WinNT.HANDLE) : Process {

    private val modulesMap = Collections.synchronizedMap(Object2ObjectArrayMap<String, WindowsModule>())

    override fun loadModules() {
        modulesMap.clear()

        val modules = arrayOfNulls<WinDef.HMODULE>(4096) // support for Windows Creator's Update
        val needed = IntByReference()
        if (Psapi.EnumProcessModulesEx(handle, modules, modules.size, needed)) {
            for (i in 0..needed.value / Native.getNativeSize(WinDef.HMODULE::class.java)) {
                val hModule = modules[i] ?: continue
                val info = com.sun.jna.platform.win32.Psapi.MODULEINFO()
                if (JNAPsapi.GetModuleInformation(handle, hModule, info, info.size())) {
                    val address = JNAPointer.nativeValue(hModule.pointer)
                    val module = WindowsModule(address, this, hModule, info)
                    modulesMap.put(module.name, module)
                }
            }
        }
    }

    override val modules: Map<String, WindowsModule> = modulesMap

    override fun read(address: JNAPointer, data: JNAPointer, bytesToRead: Int) =
        Kernel32.ReadProcessMemory(handle.pointer, address, data, bytesToRead, 0) > 0

    override fun write(address: JNAPointer, data: JNAPointer, bytesToWrite: Int) =
        Kernel32.WriteProcessMemory(handle.pointer, address, data, bytesToWrite, 0) > 0

}