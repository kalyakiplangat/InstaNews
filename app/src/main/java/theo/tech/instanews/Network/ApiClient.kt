package theo.tech.instanews.Network

import android.os.Build
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import theo.tech.instanews.BuildConfig
import java.util.concurrent.TimeUnit

/**
 * created by theop
 * on 4/3/2019 at 6:02 PM
 */
class ApiClient {
    companion object {
        fun create():ApiService{
           val interceptor=Interceptor{chain ->
               val request=
                   chain.request().newBuilder().addHeader("Accept","application/json")?.addHeader("Content-Type","application/json")?.addHeader("X-Api-Key","798d4409f2b84564ab9ee2c1943e6fc6")?.build()
               chain.proceed(request)
           }
            val client=OkHttpClient.Builder()
                .connectTimeout(60,TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .addNetworkInterceptor(interceptor)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level=if (BuildConfig.DEBUG)HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                })
                .build()
            val gson=GsonBuilder()
                .setLenient()
                .setPrettyPrinting()
                .create()
            val retrofit=Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .baseUrl(Urls.Baseurl)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}