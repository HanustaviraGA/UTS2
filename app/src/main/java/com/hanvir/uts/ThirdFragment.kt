package com.hanvir.uts

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hanvir.uts.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {

    val args: ThirdFragmentArgs by navArgs()
    private var uri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third, container, false)

        val data1 = args.nama
        val data2 = args.usia
        val data3 = args.alamat
        val data4 = args.email

        var nama = view?.findViewById<TextView>(R.id.userDataNama) as TextView
        var usia = view?.findViewById<TextView>(R.id.userDataUsia) as TextView
        var alamat = view?.findViewById<TextView>(R.id.userDataAlamat) as TextView
        var email = view?.findViewById<TextView>(R.id.userDataEmail) as TextView
        var picture = view?.findViewById<ImageView>(R.id.imageView2) as ImageView

        nama.text = data1
        usia.text = data2
        alamat.text = data3
        email.text = data4

        val sharedPreferences : SharedPreferences = view.context.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val photo = sharedPreferences.getString("UserImage", "")
        uri = Uri.parse(photo)
        picture.setImageURI(uri)

        return view
    }

}