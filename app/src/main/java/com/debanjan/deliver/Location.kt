package com.debanjan.deliver

import com.fasterxml.jackson.annotation.JsonProperty

data class Location(
        @JsonProperty("lat") val lat: Double,
        @JsonProperty("lng") val lng: Double,
        @JsonProperty("address") val address: String
)