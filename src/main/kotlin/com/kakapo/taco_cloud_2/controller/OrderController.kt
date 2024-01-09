package com.kakapo.taco_cloud_2.controller

import com.kakapo.taco_cloud_2.model.TacoOrder
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
class OrderController {

    private val log = LoggerFactory.getLogger(OrderController::class.java)

    @GetMapping("/current")
    fun orderForm(model: Model): String {
        model.addAttribute("tacoOrder", TacoOrder())
        return "orderForm"
    }

    @PostMapping
    fun processOrder(@Valid @ModelAttribute tacoOrder: TacoOrder, errors: Errors, sessionStatus: SessionStatus): String {
        if (errors.hasErrors()) {
            return "orderForm"
        }

        log.info("Order submitted: {}", tacoOrder)
        sessionStatus.setComplete()

        return "redirect:/"
    }
}