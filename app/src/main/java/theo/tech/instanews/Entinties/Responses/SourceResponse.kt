package theo.tech.instanews.Entinties.Responses

import com.google.gson.annotations.SerializedName
import theo.tech.instanews.Entinties.Category

data class SourceResponse(
    @SerializedName("sources")
    var sources: List<Category>,
    @SerializedName("status")
    var status: String // ok
)