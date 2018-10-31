package life.cheat.sources

interface Process : Source {

    val id: Int

    val modules: Map<String, Module>

    fun loadModules()

}