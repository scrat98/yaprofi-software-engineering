package golovko.artem.profi.service.persister

import mu.KLogging
import org.elasticsearch.ElasticsearchException
import org.elasticsearch.action.ActionListener
import org.elasticsearch.action.bulk.BulkRequest
import org.elasticsearch.action.bulk.BulkResponse
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestHighLevelClient
import java.util.function.Consumer

class ElasticPersister(
  private val client: RestHighLevelClient
) {

  private companion object : KLogging()

  fun save(
    datasource: String,
    data: Collection<Any>,
    onFailure: Consumer<Throwable>,
    onSuccess: Runnable
  ) {
    if (data.isEmpty()) {
      return
    }

    logger.debug { "Sending ${data.size} values for datasource $datasource" }
    val request = BulkRequest()
    data.forEach { _ ->
      val index = IndexRequest(datasource).source(data)
      request.add(index)
    }
    client.bulkAsync(
      request,
      RequestOptions.DEFAULT,
      BulkListener(datasource, onFailure, onSuccess)
    )
  }

  private class BulkListener(
    private val datasource: String,
    private val onFailure: Consumer<Throwable>,
    private val onSuccess: Runnable
  ) : ActionListener<BulkResponse> {
    override fun onResponse(response: BulkResponse) {
      if (response.hasFailures()) {
        val failure = ElasticsearchException(response.buildFailureMessage())
        executeHandlersOnResult(Result.failure(failure))
      }
      executeHandlersOnResult(Result.success(response))
    }

    override fun onFailure(failure: Exception) {
      executeHandlersOnResult(Result.failure(failure))
    }

    private fun executeHandlersOnResult(result: Result<BulkResponse>) {
      result.onSuccess {
        executeHandler { onSuccess.run() }
      }.onFailure {
        logger.error(it) { "Error while sending next data for datasource `$datasource`" }
        executeHandler { onFailure.accept(it) }
      }
    }

    private fun executeHandler(handler: Runnable) {
      runCatching {
        handler.run()
      }.onFailure {
        logger.error(it) { "Error while executing handler" }
      }
    }
  }
}

