package udb.edu.sv.dsm.parteii

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retro {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val apiService: API by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API::class.java)
    }
}
