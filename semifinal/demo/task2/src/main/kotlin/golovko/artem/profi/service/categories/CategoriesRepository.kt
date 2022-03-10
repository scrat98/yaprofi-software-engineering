package golovko.artem.profi.service.categories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface CategoriesRepository : JpaRepository<CategoryDao, Long>, JpaSpecificationExecutor<CategoryDao>
