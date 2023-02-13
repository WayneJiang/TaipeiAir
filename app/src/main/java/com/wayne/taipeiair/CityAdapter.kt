package com.wayne.taipeiair

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wayne.taipeiair.databinding.LayoutCityBinding
import com.wayne.taipeiair.repository.entity.CityEntity

class CityAdapter(private val value: Int) :
    ListAdapter<CityEntity, CityAdapter.CityViewHolder>(
        CityDiffUtilCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            LayoutCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(getItem(position), value)
    }

    inner class CityViewHolder(private val layoutCityBinding: LayoutCityBinding) :
        RecyclerView.ViewHolder(layoutCityBinding.root) {
        fun bind(cityEntity: CityEntity, value: Int) = with(layoutCityBinding) {
            tvName.text = cityEntity.name

            tvValue.text =
                "${
                    when (value) {
                        1 -> {
                            cityEntity.value1
                        }
                        2 -> {
                            cityEntity.value2
                        }
                        3 -> {
                            cityEntity.value3
                        }
                        4 -> {
                            cityEntity.value4
                        }
                        5 -> {
                            cityEntity.value5
                        }
                        6 -> {
                            cityEntity.value6
                        }
                        else -> {
                            cityEntity.value7
                        }
                    }
                }"
        }
    }
}

private class CityDiffUtilCallback : DiffUtil.ItemCallback<CityEntity>() {
    override fun areItemsTheSame(oldItem: CityEntity, newItem: CityEntity) =
        (newItem.name == oldItem.name)

    override fun areContentsTheSame(
        oldItem: CityEntity,
        newItem: CityEntity
    ): Boolean =
        (newItem.yearMonth == oldItem.yearMonth) and
                (newItem.value1 == oldItem.value1) and
                (newItem.value2 == oldItem.value2) and
                (newItem.value3 == oldItem.value3) and
                (newItem.value4 == oldItem.value4) and
                (newItem.value5 == oldItem.value5) and
                (newItem.value6 == oldItem.value6) and
                (newItem.value7 == oldItem.value7)
}