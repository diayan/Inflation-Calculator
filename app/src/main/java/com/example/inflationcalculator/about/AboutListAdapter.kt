package com.example.inflationcalculator.about

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.inflationcalculator.data.HistoricalDataSources
import com.example.inflationcalculator.databinding.LayoutAboutItemBinding

class AboutListAdapter(): ListAdapter<HistoricalDataSources, AboutListAdapter.HistoricalDataViewHolder>(DiffCallback){

    class HistoricalDataViewHolder(private var binding: LayoutAboutItemBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(historicalDataSources: HistoricalDataSources){
            binding.historicalDataSources = historicalDataSources
            //this forces binding to execute immediately, which allows recyclerview to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [HistoricalDataSources]
     * has been updated.
     */
    companion object DiffCallback: DiffUtil.ItemCallback<HistoricalDataSources>(){
        override fun areItemsTheSame(oldItem: HistoricalDataSources, newItem: HistoricalDataSources): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: HistoricalDataSources, newItem: HistoricalDataSources): Boolean {
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