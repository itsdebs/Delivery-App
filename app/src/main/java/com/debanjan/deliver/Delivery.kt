package com.debanjan.deliver

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
@Entity(primaryKeys = arrayOf("lat","lng"))
data class Delivery(
        @JsonProperty("description") val description: String,
        @JsonProperty("imageUrl") val imageUrl: String,
        @Embedded @JsonProperty("location") val location: Location
){
    override fun equals(other: Any?): Boolean {
        return other is Delivery && other.location.lat == location.lat && other.location.lng == location.lng
    }

    override fun hashCode(): Int {
        return location.hashCode()
    }
}