package com.example.labfinal.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class EntryProfileDto(
    val data: EntryDto,
    val message: String? = null,
    val status: Int? = null
)
