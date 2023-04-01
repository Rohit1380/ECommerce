package com.rohit.e_commerce

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rohit.e_commerce.databinding.SubcategorylistBinding
import kotlin.coroutines.coroutineContext

class SubCategoryAdapter (val subcategorylist: ArrayList<SubCategoryModel>):RecyclerView.Adapter<SubCategoryAdapter.viewHolder>(){
    class viewHolder(val binding: SubcategorylistBinding):RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubCategoryAdapter.viewHolder {
    val binding=SubcategorylistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubCategoryAdapter.viewHolder, position: Int) {
      holder.binding.tvName3.text=subcategorylist[position].name
      holder.binding.ivView1.textDirection=subcategorylist[position].image.hashCode()

    }

    override fun getItemCount(): Int {
        return subcategorylist.size
    }

    companion object {
        fun notifyDatasetChamnged() {
            TODO("Not yet implemented")
        }
    }

}