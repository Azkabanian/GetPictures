package android.getpictures.data

import android.getpictures.api.PicturesApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PicturesRepository @Inject constructor (private val picturesApi: PicturesApi) {

    fun getPhotoResults() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {GetPicturesPagingSource(picturesApi) }
        ).flow
}