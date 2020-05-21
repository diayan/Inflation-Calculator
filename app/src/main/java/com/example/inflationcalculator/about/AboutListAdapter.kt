package com.example.inflationcalculator.about

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.inflationcalculator.data.HistoricalDataSource
import com.example.inflationcalculator.databinding.LayoutAboutItemBinding

class AboutListAdapter(): ListAdapter<HistoricalDataSource, AboutListAdapter.HistoricalDataViewHolder>(DiffCallback){

    class HistoricalDataViewHolder(private var binding: LayoutAboutItemBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(historicalDataSource: HistoricalDataSource){
            binding.historicalDataSource = historicalDataSource
            //this forces binding to execute immediately, which allows recyclerview to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [HistoricalDataSource]
     * has been updated.
     */
    companion object DiffCallback: DiffUtil.ItemCallback<HistoricalDataSource>(){
        override fun areItemsTheSame(oldItem: HistoricalDataSource, newItem: HistoricalDataSource): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: HistoricalDataSource, newItem: HistoricalDataSource): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricalDataViewHolder {
        return HistoricalDataViewHolder(LayoutAboutItemBinding.inflate(LayoutInflater.from(parent.context)))
    }


    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: HistoricalDataViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}