package ca.tetervak.donutdata.domain

import java.util.*

data class Donut(
    val id: String?,
    val name: String,
    val description: String,
    val rating: Float,
    val lowFat: Boolean = false,
    var date: Date? = Date()
)