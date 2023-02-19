package com.example.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.DisplayPhoto
import android.widget.Toast
import com.example.admin.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Delete : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteButton.setOnClickListener{
            val phone = binding.deletePhone.text.toString()
            if (phone.isNotEmpty()) {
                deleteData(phone)
            } else {
                Toast.makeText(this, "Gagal terhapus!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun deleteData(phone: String) {
        databaseRef = FirebaseDatabase.getInstance().getReference("Data pribadi")
        databaseRef.child(phone).removeValue().addOnSuccessListener {
            binding.deletePhone.text.clear()
            Toast.makeText(this, "Terhapus", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Tidak dapat dihapus!!!", Toast.LENGTH_SHORT).show()
        }
    }
}