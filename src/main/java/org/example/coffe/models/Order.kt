package org.example.coffe.models

import java.time.LocalDateTime
import java.util.UUID

data class Order(
    val id: String = UUID.randomUUID().toString(),
    val email: String,
    val drink: String,
    val size: String,
    val price: Int,
    val time: LocalDateTime,
    var status: OrderStatus
) {
    constructor(
        email: String,
        drink: String,
        size: String,
        price: Int,
        time: LocalDateTime,
        status: OrderStatus
    ) : this(UUID.randomUUID().toString(), email, drink, size, price, time, status)
}