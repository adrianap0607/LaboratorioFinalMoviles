package com.example.labfinal.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class EntryListDto(
    val data: List<EntryDto>
)
