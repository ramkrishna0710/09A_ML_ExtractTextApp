package `in`.ram.mlextractlanguage

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class MainActivity : AppCompatActivity() {

    lateinit var result: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val camera = findViewById<ImageView>(R.id.iv_camera)
        val erase = findViewById<ImageView>(R.id.iv_edit)
        val copy = findViewById<ImageView>(R.id.btn_copy)
        val crop = findViewById<ImageView>(R.id.btnCrop)

        result = findViewById(R.id.et_result)
        camera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(packageManager) != null){
                startActivityForResult(intent, 123)
            } else {
                Toast.makeText(this, "Oops!! Something went wrong...", Toast.LENGTH_SHORT).show()
            }
        }

        erase.setOnClickListener {
            result.setText("")
        }

        copy.setOnClickListener {
            val clipBoard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", result.text.toString())
            clipBoard.setPrimaryClip(clip)
            Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 123 && resultCode == RESULT_OK){
            val extras = data?.extras
            val bitmap = extras?.get("data") as Bitmap
            detectTextUsingML(bitmap)
        }
    }

    private fun getImageUri(context: Context, image: Bitmap): Uri {
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, image, "Title", null)
        return Uri.parse(path.toString())
    }

    private fun detectTextUsingML(bitmap: Bitmap) {
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        val image = InputImage.fromBitmap(bitmap, 0)

        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                // Task completed successfully
                result.setText(visionText.text.toString())
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                // ...
                Toast.makeText(this, "Oops! Some thing went wrong...", Toast.LENGTH_SHORT).show()
            }
    }
}