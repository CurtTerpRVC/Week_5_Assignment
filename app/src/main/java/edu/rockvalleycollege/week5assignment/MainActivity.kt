/*
 Name: Curt Terpstra
 Class: CIS-245-OA010 (Spring 2021)
 App: Week 5 Assignment
*/

package edu.rockvalleycollege.week5assignment

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txtName = findViewById<EditText>(R.id.txtName)
        var txtEMail = findViewById<EditText>(R.id.txtEMail)
        var btnSubmit = findViewById<Button>(R.id.btnSubmit)
        var rbMale = findViewById<RadioButton>(R.id.rbMale)
        var rbFemale = findViewById<RadioButton>(R.id.rbFemale)
        var txtResult = findViewById<TextView>(R.id.txtResult)

        btnSubmit.setOnClickListener {

            hideKeyboard()
            txtResult.text = ""
            txtName.requestFocus()

            if (txtName.text.isNotEmpty() && txtEMail.text.isNotEmpty() && (rbMale.isChecked || rbFemale.isChecked)) {

                // Send the correct content to the message builder
                if (rbMale.isChecked) {
                    txtResult.text =
                        results(
                            txtName.text.toString(),
                            txtEMail.text.toString(),
                            "mens",
                            "13th"
                        )
                } else if (rbFemale.isChecked) {
                    txtResult.text =
                        results(
                            txtName.text.toString(),
                            txtEMail.text.toString(),
                            "ladies",
                            "5th"
                        )
                }

                txtName.text.clear()
                txtEMail.text.clear()
                txtName.requestFocus()

            } else {
                if (txtName.text.isEmpty() || txtEMail.text.isEmpty()) {
                    txtResult.text =
                        "Either the name or email address field is blank. This is not allowed."
                } else {
                    txtResult.text = "You need to select mail or female"
                }
            }
        }// End of Onclick Listener


        // This code goes at the end of OnCreate
        findViewById<View>(android.R.id.content).setOnTouchListener { _, event ->
            hideKeyboard()
            false
        }// End of hidekeyboard findviewbyid

    }// end of Oncreate

        //function to hide keyboard goes right before the last right bracket of Class MainActivity
        //should auto import android.content.Context
        //should auto add import android.view.inputmethod.InputMethodManager
        fun hideKeyboard() {
            try {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            } catch (e: Exception) {
                // TODO: handle exception
            }
        }// end of hide keyboard

        // Build the message with the parameters received.
        fun results(name: String, email: String, gender: String, floor: String): String {

            var message =
                "Welcome $name. The $gender lounge is on the $floor floor. We will send your meeting updates to $email "
            return message
        }//end of results
    }// end of Main Activity
