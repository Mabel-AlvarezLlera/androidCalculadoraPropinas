package com.example.calculadorapropinas

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.containsString

/**
 * Realización de test de instrumentación
 */

// Para correr las pruebas en AndroidJUnit4
@RunWith(AndroidJUnit4::class)
class CalculadoraTest {
    // Primero vamos a probar la funcionalidad para obtener una propina del 20%

    // Solo tenemos una actividad (MainActivity), para interactuar con ella hay que iniciarla
    // Con get:Rule especificamos que la regla posterior (iniciar una actividad) debe ejecutarse antes
    // de cada prueba de la clase
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    // La lógica de la prueba
    @Test
    fun calculate_20_percent_tip() {
        // El texto
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("50.00")).perform(ViewActions.closeSoftKeyboard())

        // El botón calcular
        onView(withId(R.id.boton_calcular)).perform(click())

        // Comprobar con una aserción que se muestra la propina correcta
        onView(withId(R.id.resultado_propina)).check(matches(withText(containsString("$10.0"))))
    }
}