package com.wayne.taipeiair

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wayne.taipeiair.databinding.LayoutCityBinding
import com.wayne.taipeiair.repository.entity.CityEntity

class CityAdapter :
    ListAdapter<CityEntity, CityAdapter.CityViewHolder>(CityDiffUtilCallback()) {

    var value = 1

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

            tvYearMonth.text = cityEntity.yearMonth

            when (value) {
                1 -> {
                    tvValue.text =
                        if (cityEntity.value1 < 0) {
                            "--"
                        } else {
                            "${cityEntity.value1}"
                        }
                    tvCategory.text = itemView.context.getString(R.string.value_1)
                }
                2 -> {
                    tvValue.text =
                        if (cityEntity.value2 < 0) {
                            "--"
                        } else {
                            "${cityEntity.value2}"
                        }
                    tvCategory.text = itemView.context.getString(R.string.value_2)
                }
                3 -> {
                    tvValue.text =
                        if (cityEntity.value3 < 0) {
                            "--"
                        } else {
                            "${cityEntity.value3}"
                        }
                    tvCategory.text = itemView.context.getString(R.string.value_3)
                }
                4 -> {
                    tvValue.text =
                        if (cityEntity.value4 < 0) {
                            "--"
                        } else {
                            "${cityEntity.value4}"
                        }
                    tvCategory.text = itemView.context.getString(R.string.value_4)
                }
                5 -> {
                    tvValue.text =
                        if (cityEntity.value5 < 0) {
                            "--"
                        } else {
                            "${cityEntity.value5}"
                        }
                    tvCategory.text = itemView.context.getString(R.string.value_5)
                }
                6 -> {
                    tvValue.text =
                        if (cityEntity.value6 < 0) {
                            "--"
                        } else {
                            "${cityEntity.value6}"
                        }
                    tvCategory.text = itemView.context.getString(R.string.value_6)
                }
                7 -> {
                    tvValue.text =
                        if (cityEntity.value7 < 0) {
                            "--"
                        } else {
                            "${cityEntity.value7}"
                        }
                    tvCategory.text = itemView.context.getString(R.string.value_7)
                }
            }
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