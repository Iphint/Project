package com.example.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.admin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        berikan aksi pada btn simpan
        binding.simpan.setOnClickListener{
            val nama = binding.uploadNama.text.toString()
            val operator = binding.uploadOperator.text.toString()
            val location = binding.uploadLocation.text.toString()
            val phone = binding.uploadPhone.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Data pribadi")
            val users = UserData(nama, operator, location, phone)
            databaseReference.child(phone).setValue(users).addOnSuccessListener {
                binding.uploadNama.text.clear()
                binding.uploadOperator.text.clear()
                binding.uploadLocation.text.clear()
                binding.uploadPhone.text.clear()

                Toast.makeText(this, "Telah tersimpan", Toast.LENGTH_SHORT).show()

                val i = Intent(this@UploadActivity, MainActivity::class.java)
                startActivity(i)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this, "Data gagal tersimpan!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}