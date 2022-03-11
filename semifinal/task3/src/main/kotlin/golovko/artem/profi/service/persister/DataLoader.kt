package golovko.artem.profi.service.persister

import golovko.artem.profi.database.DataProviderInfoRepository
import golovko.artem.profi.service.data.provider.synthetic.SyntheticDataProvider
import org.elasticsearch.client.RestHighLevelClient
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Service
class DataLoader(
  private val dataProviderInfoRepository: DataProviderInfoRepository,
  private val elasticClient: RestHighLevelClient,
) {
  private val scheduler = Executors.newScheduledThreadPool(10)

  private val persister = ElasticPersister(elasticClient)

  @EventListener(ApplicationReadyEvent::class)
  fun start() {
    val providersConnect = dataProviderInfoRepository.findAll()
    val providers = providersConnect.map {
      it to SyntheticDataProvider()
    }
    providers.forEach { (connection, provider) ->
      scheduler.scheduleAtFixedRate({
        val nextData = provider.get()
        persister.save(connection.name, nextData, {}, {})
      }, 0, connection.syncIntervalSeconds, TimeUnit.SECONDS)
    }
  }
}
