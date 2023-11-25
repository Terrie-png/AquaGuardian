import java.io.InputStreamReader

object Helper {
    fun readFileResource(path: String): String {
        return this::class.java.getResourceAsStream(path)?.bufferedReader().use { it?.readText() }
            ?: throw IllegalArgumentException("Resource not found: $path")
    }
}
