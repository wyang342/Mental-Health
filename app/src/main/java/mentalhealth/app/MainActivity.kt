package mentalhealth.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val RC_SIGN_IN = 123

    private var userId = ""
    private var userName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signIn()

        text.text = "Hello $userName, good to see you!"
    }
    private fun signIn() {
        // TODO: If user is logged in, set the userId and userName. Else, log in.
        // https://firebase.google.com/docs/auth/android/firebaseui#kotlin+ktx
        if (FirebaseAuth.getInstance().currentUser==null) {
            val providers = arrayListOf(
                    AuthUI.IdpConfig.GoogleBuilder().build(),
                    AuthUI.IdpConfig.EmailBuilder().build()
            )
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build(),
                    RC_SIGN_IN)
        } else {
            val user = FirebaseAuth.getInstance().currentUser
            userId = user!!.uid
            userName = user.displayName!!
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                // TODO: Get the userId and userName and save them to the fields
                // https://firebase.google.com/docs/auth/android/firebaseui#kotlin+ktx_1
                val user = FirebaseAuth.getInstance().currentUser
                userId = user!!.uid
                userName = user.displayName!!
            }
        }
    }
}