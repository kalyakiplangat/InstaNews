package theo.tech.instanews.Network

import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import theo.tech.instanews.Entinties.Responses.BaseRespose

/**
 * created by theop
 * on 4/3/2019 at 5:48 PM
 */
interface ApiService {
    @GET("everything")
    fun getAllArticles():Flowable<BaseRespose>

    @GET("")
    fun getSourceTechCategory(
        @Query("category")category:String
    ):Observable<BaseRespose>
}