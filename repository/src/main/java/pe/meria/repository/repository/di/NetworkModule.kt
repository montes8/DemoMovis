package pe.meria.repository.repository.di


import android.content.Context
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import pe.meria.repository.BuildConfig
import pe.meria.repository.repository.api.AppNetwork
import pe.meria.repository.repository.ServiceApi
import pe.meria.repository.repository.exception.NetworkException
import pe.meria.repository.repository.utils.*
import pe.meria.usecases.repository.AppRepositoryNetwork
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { providerHttpLoggingInterceptor() }
    single { providerCache(get()) }
    single { ApiInterceptor(get()) }
    single {
        providerOkHttpClient(
            get(),
            get(),
            get()
        )
    }
    single {
        providerRetrofit(
            getProperty(
                NAME_BASE_URL
            ), get()
        )
    }
    single { providerApi(get()) }

    single<AppRepositoryNetwork> {
        AppNetwork(
            get()
        )
    }

}

fun providerApi(retrofit: Retrofit): ServiceApi {
    return retrofit.create(ServiceApi::class.java)
}

fun providerRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(baseUrl)
        .build()
}

fun providerOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    apiInterceptor: ApiInterceptor, context: Context
): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(apiInterceptor)
        .build()
}

fun providerCache(context: Context): Cache {
    val cacheSize: Long = 10485760
    return Cache(context.cacheDir, cacheSize)
}

fun providerHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    return logging
}

class ApiInterceptor(
    private val context: Context
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()
        request = request.newBuilder()
            .header("Content-Type",
                CONTENT_TYPE
            )
            .header("os", PLATFORM)
            .header("x-density", getDensity(context).toString())
            .header("x-width", getWidth(context).toString())
            .header("x-height", getHeight(context).toString())
            .build()

        try {
            return chain.proceed(request)
        } catch (ex: Exception) {
            when (ex) {
                is SocketTimeoutException -> {
                    throw NetworkException(
                        10,
                        "Se perdió la conexion.",
                        "Se perdió la conexion."
                    )
                }
                is UnknownHostException -> {
                    throw NetworkException(
                        10,
                        "Se perdió la conexion.",
                        "Se perdió la conexion."
                    )
                }
                else -> {
                    throw ex
                }
            }


        }

    }
}