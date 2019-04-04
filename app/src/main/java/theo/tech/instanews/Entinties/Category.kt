package theo.tech.instanews.Entinties

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Category(
    @SerializedName("category")
    var category: String, // general
    @SerializedName("country")
    var country: String, // au
    @SerializedName("description")
    var description: String, // Comprehensive, up-to-date Australia news coverage, aggregated from sources all over the world by Google News.
    @SerializedName("id")
    var id: String, // google-news-au
    @SerializedName("language")
    var language: String, // en
    @SerializedName("name")
    var name: String, // Google News (Australia)
    @SerializedName("url")
    var url: String // https://news.google.com
):Serializable