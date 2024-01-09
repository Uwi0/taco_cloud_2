package com.kakapo.taco_cloud_2.controller

import com.kakapo.taco_cloud_2.model.Ingredient
import com.kakapo.taco_cloud_2.model.Ingredient.Type
import com.kakapo.taco_cloud_2.model.Taco
import com.kakapo.taco_cloud_2.model.TacoOrder
import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors


@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
class DesignTacoController {
    @ModelAttribute
    fun addIngredientsToModel(model: Model) {
        val ingredients: List<Ingredient> = listOf(
            Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
            Ingredient("COTO", "Corn Tortilla", Type.WRAP),
            Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
            Ingredient("CARN", "Carnitas", Type.PROTEIN),
            Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
            Ingredient("LETC", "Lettuce", Type.VEGGIES),
            Ingredient("CHED", "Cheddar", Type.CHEESE),
            Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
            Ingredient("SLSA", "Salsa", Type.SAUCE),
            Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        )

        val types: Array<Type> = Ingredient.Type.entries.toTypedArray()
        for (type in types) {
            model.addAttribute(
                type.toString().toLowerCase(),
                filterByType(ingredients, type)
            )
        }
    }

    @ModelAttribute(name = "tacoOrder")
    fun order(): TacoOrder {
        return TacoOrder()
    }

    @ModelAttribute(name = "taco")
    fun taco(): Taco {
        return Taco()
    }

    @GetMapping
    fun showDesignForm(): String {
        return "design"
    }

    @PostMapping
    fun processTaco(
        taco: @Valid Taco?, errors: Errors,
        @ModelAttribute tacoOrder: TacoOrder
    ): String {
        if (errors.hasErrors()) {
            return "design"
        }

        tacoOrder.addTaco(taco!!)
        println("Processing taco: {}$taco")

        return "redirect:/orders/current"
    }

    private fun filterByType(
        ingredients: List<Ingredient>, type: Type
    ): Iterable<Ingredient> {
        return ingredients
            .stream()
            .filter { x: Ingredient -> x.type == type }
            .collect(Collectors.toList())
    }
}
