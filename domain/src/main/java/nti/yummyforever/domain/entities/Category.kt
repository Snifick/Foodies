package nti.yummyforever.domain.entities

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable
@Keep
@JsonClass(generateAdapter = true)
data class Category(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String
)