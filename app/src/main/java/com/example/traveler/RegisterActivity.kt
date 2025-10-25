package com.example.traveler

import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()
        val regEmailEd = findViewById<EditText>(R.id.txtRegEmail)
        val regPassEd = findViewById<EditText>(R.id.txtRegPassword)
        val regSubmitBtn = findViewById<Button>(R.id.submitForRegister)
        regSubmitBtn.setOnClickListener {
            val txtEmail = regEmailEd.text.toString()
            val txtPassword = regPassEd.text.toString()

            when {
                !isConnectedToInternet() -> {
                    Toast.makeText(this, "Please connect your internet", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword) -> {
                    Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(txtEmail) -> {
                    Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(txtPassword) -> {
                    Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
                }
                !isValidEmail(txtEmail) -> {
                    Toast.makeText(this, "Please enter a valid email!", Toast.LENGTH_SHORT).show()
                }
                txtPassword.length < 6 -> {
                    Toast.makeText(this, "Password is too short!", Toast.LENGTH_SHORT).show()
                }
                else -> registerUser(txtEmail, txtPassword)
            }
        }
    }

    private fun isConnectedToInternet(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager?
            ?: return false
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities != null && (
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                )
    }

    private fun registerUser(txtEmail: String, txtPassword: String) {
        auth.createUserWithEmailAndPassword(txtEmail, txtPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Register created", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, SignInActivity::class.java))
                    finish()
                } else {
                    val e = task.exception
                    if (e is FirebaseAuthUserCollisionException) {
                        Toast.makeText(this, "User already exists with this email", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Registration failed: ${e?.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
    }

    private fun isValidEmail(txtEmail: String): Boolean {
        val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
        return Pattern.matches(regex, txtEmail)
    }
}