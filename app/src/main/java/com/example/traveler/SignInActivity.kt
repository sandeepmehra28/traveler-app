package com.example.traveler

import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        val logName = findViewById<EditText>(R.id.txtName)
        val LogEmail = findViewById<EditText>(R.id.txtEmail)
        val logPassword = findViewById<EditText>(R.id.txtPassword)
        val logBtn = findViewById<Button>(R.id.submitForLogin)
        auth = FirebaseAuth.getInstance()
        logBtn.setOnClickListener {
//            val txtEmail = LogEmail.text.toString().trim()
//            val txtPass = logPassword.text.toString().trim()
//
//            when {
//                !isConnectedToInternet() -> {
//                    Toast.makeText(this, "Please connect your internet", Toast.LENGTH_SHORT).show()
//                }
//                txtEmail.isEmpty() || txtPass.isEmpty() -> {
//                    Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
//                }
//                !isValidEmail(txtEmail) -> {
//                    Toast.makeText(this, "Please enter a valid email!", Toast.LENGTH_SHORT).show()
//                }
//                txtPass.length < 6 -> {
//                    Toast.makeText(this, "Password is too short!", Toast.LENGTH_SHORT).show()
//                }
//                else -> {
//                    loginUser(txtEmail, txtPass)
//                }
//            }


            // after complete this project you remove this code
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            }
        }
    }

    // Login function
//    private fun loginUser(txtEmail: String, txtPass: String) {
//        auth.signInWithEmailAndPassword(txtEmail, txtPass)
//            .addOnSuccessListener {
//                Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, HomeActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
//            .addOnFailureListener {
//                Toast.makeText(this, "Login failed: ${it.message}", Toast.LENGTH_SHORT).show()
//            }
//    }

    // Email validation
    private fun isValidEmail(email: String): Boolean {
        val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
        return Pattern.matches(regex, email)
    }

    // Internet connection check
//    private fun isConnectedToInternet(): Boolean {
//        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
//        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val network = connectivityManager.activeNetwork ?: return false
//            val capabilities = connectivityManager.getNetworkCapabilities(network)
//            capabilities != null && (
//                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
//                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
//                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
//                    )
//        } else {
//            val networkInfo = connectivityManager.activeNetworkInfo
//            networkInfo != null && networkInfo.isConnected
//        }
//    }