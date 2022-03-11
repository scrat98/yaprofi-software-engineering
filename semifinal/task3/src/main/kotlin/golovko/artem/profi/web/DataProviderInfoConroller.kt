package golovko.artem.profi.web

import golovko.artem.profi.database.DataProviderInfo
import golovko.artem.profi.database.DataProviderInfoRepository
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/data-providers")
class DataProviderInfoConroller(
  private val dataProviderInfoRepository: DataProviderInfoRepository
) {

  @PostMapping
  fun add(@Valid @RequestBody request: DataProviderInfo) {
    dataProviderInfoRepository.save(request)
  }
}
