package com.example.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.admin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Update : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener{
            val referencePhone = binding.referencePhone.text.toString()
            val updateName = binding.updateName.text.toString()
            val updateOperator = binding.updateOperator.text.toString()
            val updateLocation = binding.updateLocation.text.toString()

            updateData(referencePhone,updateName,updateLocation,updateOperator)
        }
    }

    private fun updateData(phone: String, nama: String, operator: String, location: String) {
        databaseRef = FirebaseDatabase.getInstance().getReference("Data pribadi")
        val user = mapOf<String,String>(
            "nama" to nama,
            "operator" to operator,
            "location" to location
        )

        databaseRef.child(phone).updateChildren(user).addOnSuccessListener {
            binding.referencePhone.text.clear()
            binding.updateName.text.clear()
            binding.updateOperator.text.clear()
            binding.updateLocation.text.clear()

            Toast.makeText(this, "Update sukses", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Update gagal!!!", Toast.LENGTH_SHORT).show()
        }
    }
}