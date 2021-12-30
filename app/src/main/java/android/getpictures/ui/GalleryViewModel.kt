package android.getpictures.ui

import android.getpictures.data.PicturesRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val repository: PicturesRepository) :
    ViewModel() {

    val photos = repository.getPhotoResults().cachedIn(viewModelScope).asLiveData()
}