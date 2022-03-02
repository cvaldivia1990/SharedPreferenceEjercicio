package cl.desafiolatam.sharedpreferenceejercicio.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.desafiolatam.sharedpreferenceejercicio.databinding.FragmentDetalleBinding


class DetalleFragment : Fragment() {

    private lateinit var binding:FragmentDetalleBinding
    lateinit var archivoSharedPreferences: SharedPreferences
    private val ARCHIVO_NAME = "Preferencia"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetalleBinding.inflate(inflater,container,false);
        archivoSharedPreferences = requireActivity().getSharedPreferences(ARCHIVO_NAME, Context.MODE_PRIVATE)
        if(archivoSharedPreferences.all.isNotEmpty()) {
            leerDesdeSharedPreferences()
        }

        with(binding)
        {
            btnGuardar.setOnClickListener {
                guardar()
            }

            btnBorrar.setOnClickListener {
                borrar()
            }
        }

        return binding.root
    }

    private fun leerDesdeSharedPreferences() {
        with(binding){
            valorNumeroEntero.text = archivoSharedPreferences.getInt("Numero_Entero",0).toString()
            valorTexto.text = archivoSharedPreferences.getString("Texto","")
            valorNumeroDecimal.text = archivoSharedPreferences.getFloat("Numero_Decimal",0.0f).toString()
            val checked = archivoSharedPreferences.getBoolean("Switch",false)
            switchSeleccionado.isChecked = checked

        }
    }

    private fun guardar(){
        with(binding){
            if(ptNumeroEntero.text!!.isNotEmpty()) {
                val numeroEntero: Int = ptNumeroEntero.text.toString().toInt()
                archivoSharedPreferences.edit().putInt("Numero_Entero",numeroEntero).commit()
                valorNumeroEntero.text = numeroEntero.toString()
            }
            if(ptTexto.text!!.isNotEmpty()){
                val texto = ptTexto.text!!.toString()
                archivoSharedPreferences.edit().putString("Texto",texto).commit()
                valorTexto.text = texto
            }
            if(ptNumeroDecimal.text!!.isNotEmpty()){
                val numeroDecimal = ptNumeroDecimal.text.toString().toFloat()
                archivoSharedPreferences.edit().putFloat("Numero_Decimal",numeroDecimal).commit()
                valorNumeroDecimal.text = numeroDecimal.toString()
            }

            val checked = switchSeleccionado.isChecked
            archivoSharedPreferences.edit().putBoolean("Switch",checked).commit()
        }
    }

    private fun borrar(){
        with(binding){
            archivoSharedPreferences.edit().clear().commit()
            valorNumeroEntero.text = "0"
            valorTexto.text = ""
            valorNumeroDecimal.text = 0.0f.toString()
            switchSeleccionado.isChecked = false
        }
    }

}




