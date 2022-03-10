package golovko.artem.profi.service.categories

import golovko.artem.profi.web.categories.AddCategoryResponse
import golovko.artem.profi.web.categories.GetAllCategoryResponse
import golovko.artem.profi.web.categories.GetOneCategoryResponse

fun CategoryDao.toAddCategoryResponse(): AddCategoryResponse = AddCategoryResponse(
  id = id!!,
  name = name,
  description = description,
  parentId = parentId
)

fun CategoryDao.toGetAllCategoryResponse(): GetAllCategoryResponse = GetAllCategoryResponse(
  id = id!!,
  name = name,
  description = description,
  subCategories = subCategories.map { it.toGetAllCategoryResponse() }
)

fun CategoryDao.toGetOneCategoryResponse(): GetOneCategoryResponse = GetOneCategoryResponse(
  id = id!!,
  name = name,
  description = description
)
