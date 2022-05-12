package com.hanvir.uts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.hanvir.uts.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var login = view?.findViewById<Button>(R.id.loginBtn) as Button
        login.setOnClickListener {
            //        Mengenali bagian pada view
            var username = view?.findViewById<EditText>(R.id.textUsername) as EditText
            var password = view?.findViewById<EditText>(R.id.textPassword) as EditText
            //        Convert ke text
            val data1 = username.text.toString()
            val data2 = password.text.toString()
            //        Pengecekan
            if(data1 == "HanVir" && data2 == "SunibNgalam"){
                findNavController().navigate(R.id.action_FirstFragment_to_secondFragment)
            }else{
                Toast.makeText(activity, "Data tidak ditemukan", Toast.LENGTH_LONG).show()
            }
        }
    }
}