package udb.edu.sv.dsm.parteii

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.content.Intent


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UsuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = UsuAdapter()
        recyclerView.adapter = userAdapter

        findViewById<Button>(R.id.btnRefre).setOnClickListener {
            fetchUsers()
        }

        findViewById<Button>(R.id.btnCreateUser).setOnClickListener {
            startActivity(Intent(this, Usu_activity::class.java))
        }

    }

    private fun fetchUsers() {
        // llamada en segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Se realiza la llamada a la API para obtener datos
                val users = Retro.apiService.getUsers()
                withContext(Dispatchers.Main) {
                    userAdapter.setData(users)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Error al obtener datos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
