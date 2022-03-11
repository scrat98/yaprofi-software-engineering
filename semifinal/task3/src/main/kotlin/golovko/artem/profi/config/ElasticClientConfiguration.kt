package golovko.artem.profi.config

import org.apache.http.HttpHost
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ElasticClientConfiguration {

  @Bean
  fun elasticClientLocal(): RestHighLevelClient {
    val lowLevelClient = RestClient.builder(HttpHost.create("http://localhost:9200"))
    return RestHighLevelClient(lowLevelClient)
  }
}
