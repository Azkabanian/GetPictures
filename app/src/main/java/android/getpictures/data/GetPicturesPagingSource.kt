package android.getpictures.data

import android.getpictures.api.PicturesApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class GetPicturesPagingSource(
    private val picturesApi: PicturesApi
) : PagingSource<Int, String>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        return try {
            val response = picturesApi.getPictures()
            val photos = response

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exeption: IOException) {
            LoadResult.Error(exeption)
        } catch (exeption: HttpException) {
            LoadResult.Error(exeption)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, String>): Int? {
        TODO("Not yet implemented")
    }
}