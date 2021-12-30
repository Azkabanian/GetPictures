package android.getpictures.api

import retrofit2.http.GET

interface PicturesApi {

    @GET("task-m-001/list.php")
    suspend fun getPictures(): ArrayList<String>

    companion object {
        const val BASE_URL = "https://dev-tasks.alef.im/"
    }


}