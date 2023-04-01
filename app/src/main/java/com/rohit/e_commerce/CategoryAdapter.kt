package com.rohit.e_commerce

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.rohit.e_commerce.databinding.CategoryListBinding


class CategoryAdapter (val categoryList: ArrayList<CategoryModel>):RecyclerView.Adapter<CategoryAdapter.viewHolder>(){
    class viewHolder (val binding: CategoryListBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
      val binding=CategoryListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
       holder.binding.tvName1.text=categoryList[position].name
    }

}