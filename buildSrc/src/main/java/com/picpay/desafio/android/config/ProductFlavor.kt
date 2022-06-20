package com.picpay.desafio.android.config

data class ProductFlavor(
    val name: String,
    val dimension: FlavorDimension,
    val applicationIdSuffix: String = "",
    val versionNameSuffix: String = "",
    val buildConfigFields: List<ConfigField> = listOf(),
)
