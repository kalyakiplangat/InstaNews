package theo.tech.instanews.Entinties

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Article(
    @SerializedName("author")
    var author: String, // Marianne Dodson
    @SerializedName("content")
    var content: String, // DC has a new clown prince of crime.Warner Bros. on Wednesday debuted the first trailer for Joker, its new film telling the iconic Batman villain's origin story. The trailer shows off Joaquin Phoenix's take on the character, including his suitably disturbing… [+1319 chars]
    @SerializedName("description")
    var description: String, // Bitcoin is back in town — but it's unclear how it got here.The cryptocurrency's price shot up by 15 percent on Tuesday, briefly surpassing $5,000 for the first time since November, reports CNN.Founder and CEO of the deVere group Nigel Green said "there is a…
    @SerializedName("publishedAt")
    var publishedAt: String, // 2019-04-03T15:35:48Z
    @SerializedName("source")
    var source: Source,
    @SerializedName("title")
    var title: String, // Bitcoin price mysteriously soars by 15 percent
    @SerializedName("url")
    var url: String, // https://theweek.com/speedreads/833010/bitcoin-price-mysteriously-soars-by-15-percent
    @SerializedName("urlToImage")
    var urlToImage: String // https://images.theweek.com/sites/default/files/styles/tw_image_6_4/public/gettyimages-887657568.jpg?itok=zFGVvo5H
):Serializable