package reader

import java.nio.file.Files
import java.nio.file.Paths

class Reader {

    fun testPath(path: String): Boolean {
        try {
            Files.newBufferedReader(Paths.get(path))
            return true
        } catch(e: java.nio.file.AccessDeniedException) {
            println(e.message)
        }

        return false
    }

}