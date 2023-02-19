package client.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import client.project.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener{
            val searchPhone: String = binding.searchPhone.text.toString()
            if (searchPhone.isNotEmpty()) {
                readData(searchPhone)
            } else {
                Toast.makeText(this, "masukan nomor anda", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(phone: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Data pribadi")
        databaseReference.child(phone).get().addOnSuccessListener {
            if (it.exists()){
                val name = it.child("nama").value
                val operator = it.child("operator").value
                val location = it.child("location").value

                Toast.makeText(this, "Data ditemukan", Toast.LENGTH_SHORT).show()
                binding.searchPhone.text.clear()
                binding.readName.text = name.toString()
                binding.readOperator.text = operator.toString()
                binding.readLocation.text = location.toString()
            } else {
                binding.readName.text = null
                binding.readOperator.text = null
                binding.readLocation.text = null
                Toast.makeText(this, "Data nomor tidak tersedia di database", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
    }
}
