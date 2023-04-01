package com.rohit.e_commerce

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.rohit.e_commerce.databinding.FragmentSubCategoryBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SubCategory.newInstance] factory method to
 * create an instance of this fragment.
 */
class SubCategory : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var mainActivity: MainActivity
    lateinit var subCategoryAdapter: SubCategoryAdapter
    lateinit var binding:FragmentSubCategoryBinding
    var arrayList=ArrayList<SubCategoryModel>()
    val db=Firebase.firestore



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity=activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        db.collection("SubCategory")
            .addSnapshotListener { value, error ->
                for (doc in value!!.documentChanges){
                  when(doc.type){
                        DocumentChange.Type.ADDED->{
                            val SubCategoryModel=doc.document.toObject(SubCategoryModel::class.java)
                            SubCategoryModel.id=doc.document.id
                            arrayList.add(SubCategoryModel)
                            SubCategoryAdapter.notifyDatasetChamnged()
                        }
                      DocumentChange.Type.MODIFIED->{
                          val SubCategoryModel=doc.document.toObject(SubCategoryModel::class.java)
                          SubCategoryModel.id=doc.document.id
                          arrayList.add(SubCategoryModel)
                          subCategoryAdapter.notifyDataSetChanged()
                      }
                      DocumentChange.Type.REMOVED->{
                          val SubCategoryModel=doc.document.toObject(SubCategoryModel::class.java)
                          SubCategoryModel.id=doc.document.id
                          arrayList.add(SubCategoryModel)
                          SubCategoryAdapter.notifyDatasetChamnged()
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
        binding = FragmentSubCategoryBinding.inflate(layoutInflater)
        subCategoryAdapter = SubCategoryAdapter(arrayList)
        binding.rView.layoutManager=LinearLayoutManager(requireContext())
        binding.rView.adapter=subCategoryAdapter
        return binding.root
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SubCategory.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SubCategory().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}