package com.kakapo.taco_cloud_2.converter

import com.kakapo.taco_cloud_2.model.Ingredient
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class IngredientByIdConverter : Converter<String, Ingredient> {

    private val ingredientMap: Map<String, Ingredient> = mapOf(
        "FLTO" to Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
        "COTO" to Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
        "GRBF" to Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
        "CARN" to Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
        "TMTO" to Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
        "LETC" to Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
        "CHED" to Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
        "JACK" to Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
        "SLSA" to Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
        "SRCR" to Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
    )

    override fun convert(id: String): Ingredient? {
        return ingredientMap[id]
    }
}