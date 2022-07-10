package dev.mvgioser.pc2ss

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val etDNI = findViewById<EditText>(R.id.etDNI)
        val etClave: TextView = findViewById(R.id.etClave)
        val btnIngresar: Button = findViewById(R.id.btnIngresar)
        val btnCrearCuenta: Button = findViewById(R.id.btnCrearCuenta)
        val db = FirebaseAuth.getInstance()

        btnIngresar.setOnClickListener{
            var dni: String = etDNI.text.toString()
            var clave: String = etClave.text.toString()

            db.signInWithEmailAndPassword(dni, clave).
            addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    Toast.makeText(this,"ACCESO PERMITIDO", Toast.LENGTH_LONG).show()
                } else{
                    Toast.makeText(this,"EL USUARIO Y/O CLAVE NO EXISTE EN EL SISTEMA", Toast.LENGTH_LONG).show()
                }
            }
        }
        btnCrearCuenta.setOnClickListener {
            val intent = Intent(this, PrincipalActivity::class.java)
            startActivity(intent)
        }
    }
}