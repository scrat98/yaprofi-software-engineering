package golovko.artem.profi.web.categories

import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

data class AddCategoryRequest(
  @NotNull val name: String,
  @NotNull val description: String,
  val parentId: Long?
)

data class AddCategoryResponse(
  @NotNull val id: Long,
  @NotNull val name: String,
  @NotNull val description: String,
  @Null val parentId: Long?,
)

data class GetAllCategoryResponse(
  @NotNull val id: Long,
  @NotNull val name: String,
  @NotNull val description: String,
  @NotNull val subCategories: Collection<GetAllCategoryResponse>
)

data class GetOneCategoryResponse(
  @NotNull val id: Long,
  @NotNull val name: String,
  @NotNull val description: String,
)

data class UpdateCategoryRequest(
  @NotNull val id: Long,
  @NotNull val name: String,
  @NotNull val description: String,
  @Null val parentId: Long?
)
