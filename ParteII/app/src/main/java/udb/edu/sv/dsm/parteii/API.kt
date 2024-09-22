package udb.edu.sv.dsm.parteii

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface API{
    @GET("users")
    suspend fun getUsers(): List<User>

    @POST("users")
    suspend fun createUser(@Body newUser: User): User
}