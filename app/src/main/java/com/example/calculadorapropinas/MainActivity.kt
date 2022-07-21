package com.example.calculadorapropinas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculadorapropinas.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Listener para el botón de calcular, llamando a la función que calcula la propina
        binding.botonCalcular.setOnClickListener { calcularPropina() }
    }

    private fun calcularPropina() {
        val stringCosteDelServicio = binding.costOfServiceEditText.text.toString() // Hay que hacer toString ya que si no es de tipo Editable

        val coste = stringCosteDelServicio.toDoubleOrNull() // Para convertir el String a Double

        // Para controlar si el valor de coste es null
        if (coste == null) {
            mostrarPropina(0.0) // Para que vacie el campo
            return // Aquí podríamos mostrar un mensaje de "Introduzca un valor"
        }

        val tipPorcentage = when (binding.opcionesPropina.checkedRadioButtonId) { // Obtenemos la opción seleccionada
            R.id.opcion_veinte_por_ciento -> 0.20
            R.id.opcion_18_por_ciento -> 0.18
            else -> 0.15
        } // Para almacenar el porcentaje
        var propina = tipPorcentage * coste // Para calcular la propina, aquí usamos var ya que puede cambiar si lo redondeamos

        // Si hay que redondear
        if (binding.switchRedondear.isChecked) {
            propina = kotlin.math.ceil(propina)
        }

        // Mostramos la propina
        mostrarPropina(propina)
    }

    private fun mostrarPropina(propina: Double) {
        // Damos formato a los números
        val propinaFormateada = NumberFormat.getCurrencyInstance().format(propina)
        // Mostramos el resultado
        binding.resultadoPropina.text = getString(R.string.propina_resultante, propinaFormateada)
    }
}