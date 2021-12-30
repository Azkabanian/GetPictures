package android.getpictures.ui.gallery

import android.content.res.Configuration
import android.getpictures.R
import android.getpictures.databinding.FragmentGalleryBinding
import android.getpictures.ui.GalleryViewModel
import android.getpictures.ui.GetPicturesPhotoAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private val viewModel by viewModels<GalleryViewModel>()
    private val galleryAdapter by lazy { GetPicturesPhotoAdapter() }
    private lateinit var binding: FragmentGalleryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        galleryAdapter.setOnClickListener {
            val bundle = Bundle().apply {
                putString("photo",it)
            }
            findNavController().navigate(
                R.id.action_galleryFragment_to_detailsFragment,
                bundle
            )
        }

        viewModel.photos.observe(viewLifecycleOwner) {
            galleryAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }


    private fun initRecycler() {
        with(binding) {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = galleryAdapter
            when (resources.configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> recyclerView.layoutManager =
                    GridLayoutManager(requireContext(), 3)
                Configuration.ORIENTATION_PORTRAIT -> recyclerView.layoutManager =
                    GridLayoutManager(requireContext(), 2)
            }

        }
    }

}