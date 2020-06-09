package cr.ac.ucr.lab_4

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val send_information = findViewById(R.id.btn_send_information) as Button
        val btnSendPicture = findViewById(R.id.btn_send_information) as Button


        send_information.setOnClickListener {
            var name = findViewById(R.id.et_name) as EditText
            var temperature = findViewById(R.id.sw_temperature) as Switch
            var cough = findViewById(R.id.sw_cough) as Switch
            var migraine = findViewById(R.id.sw_migraine) as Switch
            var fatigue = findViewById(R.id.sw_fatigue) as Switch
            var loss_smell = findViewById(R.id.sw_loss_smell) as Switch
            var loss_taste = findViewById(R.id.sw_loss_taste) as Switch
            var value = "";
            if (temperature.isChecked) {
                value =
                    value + "\n" + (findViewById(R.id.txt_temperature) as TextView).text.toString();
            }
            if (cough.isChecked) {
                value = value + "\n" + (findViewById(R.id.txt_cough) as TextView).text.toString();
            }
            if (migraine.isChecked) {
                value =
                    value + "\n" + (findViewById(R.id.txt_migraine) as TextView).text.toString();
            }
            if (fatigue.isChecked) {
                value = value + "\n" + (findViewById(R.id.txt_fatigue) as TextView).text.toString();
            }
            if (loss_smell.isChecked) {
                value =
                    value + "\n" + (findViewById(R.id.txt_loss_smell) as TextView).text.toString();
            }
            if (loss_taste.isChecked) {
                value =
                    value + "\n" + (findViewById(R.id.txt_loss_taste) as TextView).text.toString();
            }

            sendMail(name.text.toString() + "\n" + value)


        }


       /*btnSendPicture.setOnClickListener {
            /*val constraintLayout: ConstraintLayout = findViewById(R.id.cl_main)
            getScreenShot(constraintLayout)*/

        }*/


    }

    private fun sendMail(name: String) {
        val myIntent = Intent(Intent.ACTION_SEND)
        myIntent.data = Uri.parse("mailto:")
        myIntent.type = "text/plain"
        myIntent.putExtra(Intent.EXTRA_SUBJECT, "Sintomas")
        myIntent.putExtra(Intent.EXTRA_TEXT, name)


        try {
            startActivity(Intent.createChooser(myIntent, "Seleccione una aplicación"))
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

   private fun getScreenShot(view: View): Bitmap {
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) bgDrawable.draw(canvas)
        else canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return returnedBitmap
    }


}


