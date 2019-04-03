package theo.tech.instanews.Entinties.Responses

import com.google.gson.annotations.SerializedName
import theo.tech.instanews.Entinties.Article

data class BaseResponse(
    @SerializedName("articles")
    var articles: List<Article>,
    @SerializedName("status")
    var status: String, // ok
    @SerializedName("totalResults")
    var totalResults: Int // 4855
)