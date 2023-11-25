package com.project.aheersapp.validation

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout


class Validation {
    companion object {
        fun validateEmail(etEmail: EditText): Boolean {
            if (etEmail.text.isNullOrEmpty()) {
                etEmail.error = "Please enter your email address."
                return false
            } else {
                //code attribution
                //the following code was taken from Stack Overflow and adapted
                //https://stackoverflow.com/questions/1819142/how-should-i-validate-an-e-mail-address
                //mindriot
                //https://stackoverflow.com/users/1011746/mindriot
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.text).matches()) {
                    etEmail.error = "Please enter a valid email address."
                    return false
                } else {
                    return true
                }
            }
        }

        fun validateInput(etInput: EditText, message : String): Boolean{
            if (etInput.text.isNullOrEmpty()) {
                etInput.error = message
                return false
            } else {
                etInput.error = null
                return true
            }
        }

        fun validatePasswordSignIn(etPassword: EditText, textInputLayoutPassword: TextInputLayout): Boolean{
            if (etPassword.text.isNullOrEmpty()) {
                textInputLayoutPassword.setEndIconMode(TextInputLayout.END_ICON_NONE)
                etPassword.error = "Please enter your password."
                return false
            }
            else{
                textInputLayoutPassword.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE)
                return true
            }
        }

        fun validateConfirmPassword(etPassword: EditText, etConfirmPassword: EditText): Boolean{
            if(etPassword.text.length > 0){
                if(!etPassword.text.toString().equals(etConfirmPassword.text.toString())){
                    etConfirmPassword.error = "Passwords do not match."
                    return false
                }
                else{
                    return true
                }
            }
            else{
                return false
            }
        }
    }
}