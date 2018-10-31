@file:JvmName("Main")

package life.cheat

import com.sun.jna.platform.win32.WinNT
import life.cheat.sources.windows.Windows

fun main() {
    val csgo = Windows.openProcess(
        "csgo.exe",
        WinNT.PROCESS_QUERY_INFORMATION or WinNT.PROCESS_VM_READ or WinNT.PROCESS_VM_WRITE
    )!!
    csgo.loadModules()
    val clientDLL = csgo.modules["client_panorama.dll"]!!

    val dwForceJump = 0x50E13BCL
    clientDLL[dwForceJump] = 5
}