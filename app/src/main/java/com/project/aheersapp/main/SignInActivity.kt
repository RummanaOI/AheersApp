package com.project.aheersapp.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.project.aheersapp.MainActivity
import com.project.aheersapp.databinding.ActivitySignInBinding
import com.project.aheersapp.validation.Validation

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var mAuth : FirebaseAuth
    private var isAllFieldsValid : Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            etEmail.afterTextChanged {
                Validation.validateEmail(binding.etEmail)
            }

            etPassword.afterTextChanged {
                val isPasswordValid =  Validation.validatePasswordSignIn(etPassword, textinputlayoutPassword)
            }

            btnSignIn.setOnClickListener(){
                val isEmailValid = Validation.validateEmail(etEmail)
                val isPasswordValid = Validation.validatePasswordSignIn(etPassword, textinputlayoutPassword)

                if(isEmailValid && isPasswordValid)
                {
                    authenticateUser()
                }
                else {
                    Toast.makeText(applicationContext, "Please complete all fields.", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun authenticateUser(){
        val email : String = binding.etEmail.text.toString()
        val password : String = binding.etPassword.text.toString()
        var exceptionMessage : String? = null
        mAuth = FirebaseAuth.getInstance()
        try{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(){
                if(it.isSuccessful){
                    // Toast.makeText(applicationContext, "Here", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this, "User sign in successful.", Toast.LENGTH_SHORT).show()
                    //   GlobalMethods.userUID = mAuth.currentUser?.uid.toString()
                    val mainActivity = Intent(this, MainActivity::class.java)
                    // mainActivity.putExtra("userUID",mAuth.currentUser?.uid.toString());
                    startActivity(mainActivity)
                    finish()
                }
                else{
                    exceptionMessage = it.exception?.message.toString()
                }
            }.addOnFailureListener(){
                Toast.makeText(this, "User sign in failed. " + exceptionMessage, Toast.LENGTH_SHORT).show()
            }
        }
        catch (ex : Exception){
            Toast.makeText(this, ex.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}