package com.moose.mindvalley.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Categories(
    val `data`: CategoriesData
)

data class CategoriesData(
    val categories: List<Category>
)

data class Category(
    val name: String
)

@Entity(tableName = "db_categories")
data class DbCategories(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val categories: String
)