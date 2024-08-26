package com.example.moviebox.ui.main.seriesAdapater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebox.R
import com.example.moviebox.databinding.ViewHolderItemFilmBinding
import com.example.moviebox.model.popularSeries.PopularSeriesModel
import com.example.moviebox.model.popularSeries.ResultsSeriesListModel
import com.example.moviebox.utils.BASE_URL_IMAGE
import com.squareup.picasso.Picasso

class SeriesAdapter(
    val popularSeriesAdapter: PopularSeriesModel,
    val onClick2: (serial: ResultsSeriesListModel) -> Unit

) : RecyclerView.Adapter<SeriesAdapter.PopularSeriesViewHolder>() {


    inner class PopularSeriesViewHolder(itemsSeView: View) : RecyclerView.ViewHolder(itemsSeView) {
        val binding2 = ViewHolderItemFilmBinding.bind(itemsSeView)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularSeriesViewHolder {
        return PopularSeriesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_item_film, parent, false
            )
        )

    }


    override fun getItemCount(): Int = popularSeriesAdapter.results.size


    override fun onBindViewHolder(holder: PopularSeriesViewHolder, position: Int) {
        val serial = popularSeriesAdapter.results[position]
        holder.binding2.apply {
            txtTitle.text = serial.name
            txtScore.text = serial.voteAverage.toString()
            Picasso.get()
                .load(BASE_URL_IMAGE + serial.posterPath)
                .into(pic)

            root.setOnClickListener {
                onClick2.invoke(serial)
            }
        }

    }
}