package com.rohit.e_commerce

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.rohit.e_commerce.databinding.FragmentRegistrationBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Registration.newInstance] factory method to
 * create an instance of this fragment.
 */
class Registration : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var Mainactivity: MainActivity
    lateinit var binding: FragmentRegistrationBinding
    var db=Firebase.firestore
    var auth=Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            Mainactivity = activity as MainActivity
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(layoutInflater)
        binding.others.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked){
                binding.etOthers.visibility = View.VISIBLE
            }else{
                binding.etOthers.visibility = View.GONE
            }
        }
        binding.btnRegister.setOnClickListener {
            if (binding.etName.text.isEmpty()) {
                binding.etName.error = "enter name"
            } else if (binding.etNumber.text.isEmpty()) {

                binding.etNumber.error = "enter Phone Number"
            } else if (binding.etEmail.text.isEmpty()) {
                binding.etEmail.error = "enter Password"
            } else if (binding.etPass.text.isEmpty()) {
                binding.etPass.error = "Confirm Password"
            } else if (binding.Rgbtn.checkedRadioButtonId == -1) {
                Toast.makeText(requireActivity(), "Enter Gender", Toast.LENGTH_SHORT).show()
            } else {
                val userModel = UserModel(binding.etName.text.toString(),
                binding.etNumber.text.toString(),
                binding.etEmail.text.toString(),
                binding.etPass.text.toString(),
                binding.Rgbtn.toString())

                auth.createUserWithEmailAndPassword(
                    binding.etEmail.text.toString(),
                    binding.etPass.text.toString())
                    .addOnSuccessListener {

                db.collection("Register")
                    .document(auth.currentUser?.uid?:"")
                    .set(userModel)
                    .addOnSuccessListener {

                    Toast.makeText(requireActivity(), "Register Successfully", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.login)
                }.addOnFailureListener {
                    Log.e("TAG","Failure$(it)")
                    Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show()
                }

            }}
        }


        binding.tvUser.setOnClickListener {
            findNavController().navigate(R.id.login)
        }
        return binding.root

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Registration.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Registration().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}