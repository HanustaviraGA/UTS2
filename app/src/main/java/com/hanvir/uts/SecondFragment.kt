package com.hanvir.uts

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.hanvir.uts.databinding.FragmentSecondBinding
import java.io.File

class SecondFragment : Fragment() {

    private var uri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        Tombol submit
        val submit = view.findViewById<Button>(R.id.submitBtn) as Button
        val gambar = view.findViewById<Button>(R.id.imageBtn) as Button
//        Kamus
//        val photo = view.findViewById<ImageView>(R.id.foto) as ImageView
        val nama = view.findViewById<EditText>(R.id.textNama) as EditText
        val usia = view.findViewById<EditText>(R.id.textUsia) as EditText
        val alamat = view.findViewById<EditText>(R.id.textAlamat) as EditText
        val email = view.findViewById<EditText>(R.id.textEmail) as EditText
        val sharedPreferences: SharedPreferences = view.context.getSharedPreferences("pref", Context.MODE_PRIVATE)

        gambar.setOnClickListener {
            checkPermissionForImage()
            Toast.makeText(context, "Silahkan pilih gambar", Toast.LENGTH_SHORT).show()
        }

        submit.setOnClickListener {
//            Pengubahan
            val data1 = nama.text.toString()
            val data2 = usia.text.toString()
            val data3 = alamat.text.toString()
            val data4 = email.text.toString()
            if(data1.isNotEmpty() && data2.isNotEmpty() && data3.isNotEmpty() && data4.isNotEmpty()){
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("UserImage", uri.toString())
                editor.apply()
                val action = SecondFragmentDirections.actionSecondFragmentToThirdFragment(data1, data2, data3, data4)
                Navigation.findNavController(view).navigate(action)
            }else{
                Toast.makeText(activity, "Silahkan mengisi data diri", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 1)
    }

    private fun checkPermissionForImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((context?.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                && (context?.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
            ) {
                val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                val permissionCoarse = arrayListOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

                requestPermissions(permission, 100)
                requestPermissions(permission, 100)
            } else {
                openGalleryForImage()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            val foto1 = view?.findViewById<ImageView>(R.id.foto) as ImageView
            foto1.setImageURI(data?.data)
            uri = data?.data!!
        }
    }
}