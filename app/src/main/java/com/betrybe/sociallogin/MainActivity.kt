package com.betrybe.sociallogin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val emailInputText: TextInputEditText by lazy { findViewById(R.id.email_text_edit_layout) }
        val passwordInputText: TextInputEditText by lazy { findViewById(R.id.password_text_edit_layout) }
        val loginBTN: Button by lazy { findViewById(R.id.login_button) }

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Pode deixar vazio, se não precisar implementar
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val email = emailInputText.text.toString().trim()
                val password = passwordInputText.text.toString().trim()
                loginBTN.isEnabled = email.isNotEmpty() && password.isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {
                // Pode deixar vazio, se não precisar implementar
            }
        }
        emailInputText.addTextChangedListener(textWatcher)
        passwordInputText.addTextChangedListener(textWatcher)

        // Desabilitar o botão inicialmente
        loginBTN.isEnabled = false

        fun isEmailValid(email: String): Boolean {
            // fonte: https://regexr.com/3e48o
            val emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"
            return email.matches(Regex(emailRegex))
        }
        fun isPasswordValid(password: String): Boolean {
            return password.length > 4
        }

        loginBTN.setOnClickListener{
            val emailTextInputLayout: TextInputLayout = findViewById(R.id.email_text_input_layout)
            val passwordTextInputLayout: TextInputLayout = findViewById(R.id.password_text_input_layout)

            val email = emailInputText.text.toString().trim()
            val password = passwordInputText.text.toString().trim()

            if (isEmailValid(email)) {
                emailTextInputLayout.error = null
            } else {
                emailTextInputLayout.error = "Email inválido"
            }
            if (!isPasswordValid(password)) {
                passwordTextInputLayout.error = "Senha deve ter mais de 4 caracteres"
            } else {
                passwordTextInputLayout.error = null
            }
            if (isEmailValid(email) && isPasswordValid(password)) {
                showLoginSuccess(findViewById(android.R.id.content))
            }
        }
    }

    // Mover esta função para fora do onCreate
    private fun showLoginSuccess(view: View) {
        Snackbar.make(view, "Login efetuado com sucesso", Snackbar.LENGTH_LONG).show()
    }
}
