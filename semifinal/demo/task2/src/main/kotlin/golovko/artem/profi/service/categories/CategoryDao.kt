package golovko.artem.profi.service.categories

import javax.persistence.*

@Entity
@Table(name = "category")
data class CategoryDao(
  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,

  @Column(nullable = false)
  val name: String,

  @Column(nullable = false)
  val description: String,

  @Column(nullable = true)
  val parentId: Long?
) {
  @OneToMany(mappedBy = "parentId", fetch = FetchType.LAZY)
  var subCategories: Set<CategoryDao> = emptySet()
}
