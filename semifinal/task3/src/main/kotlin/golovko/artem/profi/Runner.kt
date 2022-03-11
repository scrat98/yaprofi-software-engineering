package golovko.artem.profi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Runner {
  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      runApplication<Runner>(*args)
    }
  }
}
