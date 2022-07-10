package dev.mvgioser.pc2ss.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import dev.mvgioser.pc2ss.LoginActivity
import dev.mvgioser.pc2ss.R
import dev.mvgioser.pc2ss.ui.fragments.model.CuentaModel
import java.util.*

class RegistroFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_registro, container, false)
        val etIngresarDNI: EditText = view.findViewById(R.id.etIngresarDNI)
        val etIngresarNombreCompleto: EditText = view.findViewById(R.id.etIngresarNombreCompleto)
        val etIngresarClave: EditText = view.findViewById(R.id.etIngresarClave)
        val etIngresarNuevamenteClave: EditText = view.findViewById(R.id.etIngresarNuevamenteClave)
        val btnRegistrarCuenta: Button = view.findViewById(R.id.btnRegistrarCuenta)
        val db = FirebaseFirestore.getInstance()

        btnRegistrarCuenta.setOnClickListener{
            var dni = etIngresarDNI.text.toString()
            var nombrecompleto = etIngresarNombreCompleto.text.toString()
            var clave = etIngresarClave.text.toString()
            var clavenuevamente = etIngresarNuevamenteClave.text.toString()

            val nuevaCuenta = CuentaModel(dni, nombrecompleto, clave, clavenuevamente)
            val newID: String = UUID.randomUUID().toString()

            db.collection("cuentas")
                .document(newID)
                .set(nuevaCuenta)
                .addOnSuccessListener {
                    Snackbar.make(view,"Se registró la cuenta correctamente", Snackbar.LENGTH_LONG).show()
                    val intent = Intent(activity, LoginActivity::class.java)
                    Handler().postDelayed( {
                        startActivity(intent)
                    }, 3000)
                }.addOnFailureListener{
                    Snackbar.make(view,"Error al registrar la cuenta....", Snackbar.LENGTH_LONG).show()
                }
        }
        return view
    }
}
