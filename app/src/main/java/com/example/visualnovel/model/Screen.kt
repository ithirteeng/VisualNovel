package com.example.visualnovel

data class Screen(
    val id: Int,
    var header: String,
    val background: String,
    val arrayOfVariants: List<Variants>
)

data class Variants(
    val nextId: Int,
    val variantText: String
)