package com.kakapo.taco_cloud_2.model

import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.CreditCardNumber
import java.util.Date

data class TacoOrder(
    val id: Long = 0,
    val placedAt: Date,
    @NotBlank(message = "Delivery name is required")
    val deliveryName: String = "",
    @NotBlank(message = "Street is required")
    val deliveryStreet: String = "",
    @NotBlank(message = "City is required")
    val deliveryCity: String = "",
    @NotBlank(message = "State is required")
    val deliveryState: String = "",
    @NotBlank(message = "Zip code is required")
    val deliveryZip: String = "",
    @CreditCardNumber(message = "Not a valid credit card number")
    val ccNumber: String = "",
    @Pattern(regexp = "^(0[1-9]|1[0-2])(/)([2-9][0-9])$",
        message = "Must be formatted MM/YY")
    val ccExpiration: String = "",
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    val ccCVV: String = "",
    val tacos: MutableList<Taco> = mutableListOf()
) {
    fun addTaco(taco: Taco) {
        tacos.add(taco)
    }

    companion object{
        const val SERIAL_VERSION_UID = 1L
    }
}
