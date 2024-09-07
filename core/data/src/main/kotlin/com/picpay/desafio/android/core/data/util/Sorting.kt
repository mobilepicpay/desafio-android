package com.picpay.desafio.android.core.data.util

sealed interface SortingOption {
    val parameter: String
    val displayText: String
}
enum class OrderDirection(
    override val parameter: String,
    override val displayText: String
): SortingOption {
    ASCENDING(parameter = "ASC", displayText = "A -> Z"),
    DESCENDING(parameter = "DESC", displayText = "Z -> A")
}

enum class SortBy(
    override val parameter: String,
    override val displayText: String
): SortingOption {
    NAME(parameter = "name", displayText = "Nome"),
    USERNAME(parameter = "username", displayText = "@")
}