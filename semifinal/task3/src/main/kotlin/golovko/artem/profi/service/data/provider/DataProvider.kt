package golovko.artem.profi.service.data.provider

import java.util.function.Supplier

data class DataProviderConnect(
  val jdbcUrl: String,
  val queryToRetrieve: String
)

interface DataProvider : Supplier<Collection<Any>>

interface DataProviderFactory {
  fun get(connect: DataProviderConnect): DataProvider
}
