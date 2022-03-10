package golovko.artem.profi.web.categories

import golovko.artem.profi.service.categories.*
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotNull


@Validated
@RestController
@RequestMapping("/categories")
class CategoriesController(
  private val categoriesRepository: CategoriesRepository
) {

  /*
  TODO: Error when parentId = next generated id
   */
  @PostMapping
  fun add(@Valid @RequestBody request: AddCategoryRequest): AddCategoryResponse {
    val toSave = CategoryDao(
      name = request.name,
      description = request.description,
      parentId = request.parentId
    )
    return categoriesRepository.save(toSave).toAddCategoryResponse()
  }

  @GetMapping
  fun get(): Collection<GetAllCategoryResponse> {
    return categoriesRepository.findAll().map { it.toGetAllCategoryResponse() }
  }

  @GetMapping("/{id}")
  fun get(@Valid @NotNull @PathVariable("id") id: Long): GetOneCategoryResponse {
    return categoriesRepository.getById(id).toGetOneCategoryResponse()
  }

  @PutMapping("/{id}")
  fun update(
    @Valid @NotNull @PathVariable("id") id: Long,
    @Valid @RequestBody request: UpdateCategoryRequest
  ) {
    val toUpdate = CategoryDao(
      id = id,
      name = request.name,
      description = request.description,
      parentId = request.parentId
    )
    categoriesRepository.save(toUpdate)
  }

  @DeleteMapping("/{id}")
  fun delete(@Valid @NotNull @PathVariable("id") id: Long) {
    categoriesRepository.deleteById(id)
  }

  @GetMapping
  // TODO: use jpa specification
  fun find(@Valid @NotNull @RequestParam query: String) {
    categoriesRepository.findAll()
  }
}
