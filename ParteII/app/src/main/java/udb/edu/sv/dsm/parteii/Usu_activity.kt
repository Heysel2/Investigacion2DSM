package udb.edu.sv.dsm.parteii

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Usu_activity : AppCompatActivity() {

    private lateinit var userAdapter: UsuAdapter
    private lateinit var editTextName: EditText
    private lateinit var editTextUsername: EditText
    private lateinit var editTextEmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usu)

        editTextName = findViewById(R.id.editTextName)
        editTextUsername = findViewById(R.id.editTextUsername)
        editTextEmail = findViewById(R.id.editTextEmail)
        userAdapter = UsuAdapter()

        findViewById<Button>(R.id.btnCreateUser).setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {
        val name = editTextName.text.toString()
        val username = editTextUsername.text.toString()
        val email = editTextEmail.text.toString()

        if (name.isEmpty() || username.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val newUser = User(id = 0, name = name, username = username, email = email)

        // llamada en segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Se realiza la llamada a la API para crear el usuario
                val createdUser = Retro.apiService.createUser(newUser)
                withContext(Dispatchers.Main) {
                    userAdapter.addUserData(createdUser)
                    Toast.makeText(this@Usu_activity, "Usuario creado: ${createdUser.name}", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@Usu_activity, MainActivity::class.java))
                    finish()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@Usu_activity, "Error al crear usuario", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
