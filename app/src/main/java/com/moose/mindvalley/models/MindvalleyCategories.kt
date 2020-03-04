package com.moose.mindvalley.models

data class Categories(
    val `data`: CategoriesData
)

data class CategoriesData(
    val categories: List<Category>
)

data class Category(
    val name: String
)