package com.rohit.e_commerce

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.rohit.e_commerce.databinding.FragmentCategoriesBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Categories.newInstance] factory method to
 * create an instance of this fragment.
 */
class Categories : Fragment() ,CategoryInterface{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var MainActivity:MainActivity
    var arrayList=ArrayList<CategoryModel>()
    lateinit var binding: FragmentCategoriesBinding
    lateinit var categoryInterface: CategoryInterface
    lateinit var categoryAdapter:CategoryAdapter
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivity=activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        db.collection("Category")
            .addSnapshotListener { value, error ->
                for(doc in value!!.documentChanges){
                    when(doc.type){
                        DocumentChange.Type.ADDED->{
                            val categoryModel=doc.document.toObject(CategoryModel::class.java)
                            categoryModel.id=doc.document.id
                            arrayList.add(categoryModel)
                            categoryAdapter.notifyDataSetChanged()
                        }
                        DocumentChange.Type.REMOVED->{
                            val categoryModel=doc.document.toObject(CategoryModel::class.java)
                            categoryModel.id=doc.document.id
                            arrayList.add(categoryModel)
                            categoryAdapter.notifyDataSetChanged()
                        }
                        DocumentChange.Type.MODIFIED->{
                            val CategoryModel=doc.document.toObject(CategoryModel::class.java)
                            CategoryModel.id=doc.document.id
                            arrayList.add(CategoryModel)
                            categoryAdapter.notifyDataSetChanged()
                        }
                        else -> {}
                    }
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
  binding= FragmentCategoriesBinding.inflate(layoutInflater)
        categoryAdapter= CategoryAdapter(arrayList)
        binding.recyclerView.layoutManager=LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter=categoryAdapter
        

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Categories.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Categories().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun add(position: Int) {
    findNavController().navigate(R.id.subCategory)
    }
}