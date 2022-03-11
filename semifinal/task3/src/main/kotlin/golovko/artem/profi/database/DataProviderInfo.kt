package golovko.artem.profi.database

import javax.persistence.*

@Entity
@Table(name = "data_provider_info")
data class DataProviderInfo(
  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,

  @Column(nullable = false)
  val name: String,

  @Column(nullable = false)
  val jdbcUrl: String,

  @Column(nullable = false)
  val queryToRetrieve: String,

  @Column(nullable = false)
  val syncIntervalSeconds: Long
)
