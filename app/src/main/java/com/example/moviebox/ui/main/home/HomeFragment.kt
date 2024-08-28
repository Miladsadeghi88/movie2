package com.example.moviebox.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviebox.R
import com.example.moviebox.databinding.FragmentHomeBinding
import com.example.moviebox.ui.LoginActivity
import com.example.moviebox.ui.main.adapter.Adapter
import com.example.moviebox.ui.main.adapter.RecyclerViewType
import com.example.moviebox.utils.FROM_MOVIE
import com.example.moviebox.utils.FROM_SERIES


class HomeFragment:Fragment () {
    private lateinit var binding :FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private var adapterMovies: Adapter? = null
    private var adapterSeries: Adapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPopularMovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopularSeries.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)



        viewModel.getPopularMovies()
        viewModel.movies.observe(viewLifecycleOwner) {

            adapterMovies =
                Adapter(movies = it, series = null, RecyclerViewType.fromMovie, onClick = { id->
                    startDetail(id, FROM_MOVIE)
                })
            binding.rvPopularMovies.adapter = adapterMovies
            binding.loading1.isVisible = false
            viewModel.getPopularSeries()
        }
        viewModel.series.observe(viewLifecycleOwner) {
            adapterSeries =
                Adapter(movies = null, series = it, RecyclerViewType.fromSeries, onClick = { id->
                    startDetail(id, FROM_SERIES)
                })
            binding.rvPopularSeries.adapter = adapterSeries
            binding.loading2.isVisible = false

        }



    }
    private fun startDetail(id:Int,from:Int){

        val bundle = Bundle().apply {
            putInt("from_detail", from)
            putInt("id", id)
        }
     //   requireActivity().startActivity(Intent(requireContext(), LoginActivity::class.java))
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment,bundle)

//        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
//            putExtras(bundle)
//        }
//        startActivity(intent)
    }
}