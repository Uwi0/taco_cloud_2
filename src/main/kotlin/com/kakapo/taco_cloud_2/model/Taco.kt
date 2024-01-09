package com.kakapo.taco_cloud_2.model

import jakarta.validation.constraints.Size
import java.util.Date

data class Taco(
    val id: Long = 0,
    val createdAt: Date = Date(),
    @Size(min = 5, message = "Name must be at least 5 characters long")
    val name: String = "",
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    val ingredients: List<Ingredient> = emptyList()
)
