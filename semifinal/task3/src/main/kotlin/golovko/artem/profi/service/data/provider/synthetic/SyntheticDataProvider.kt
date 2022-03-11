package golovko.artem.profi.service.data.provider.synthetic

import golovko.artem.profi.service.data.provider.DataProvider
import golovko.artem.profi.service.data.provider.DataProviderConnect
import golovko.artem.profi.service.data.provider.DataProviderFactory
import java.util.*

class SyntheticDataProviderFactory : DataProviderFactory {
  override fun get(connect: DataProviderConnect): DataProvider {
    return SyntheticDataProvider()
  }
}

class SyntheticDataProvider : DataProvider {
  override fun get(): Collection<Any> {
    return (0..100).map {
      mapOf(
        "field1" to UUID.randomUUID(),
        "field2" to UUID.randomUUID(),
        "field3" to UUID.randomUUID()
      )
    }
  }
}
