package ca.tetervak.donutdata.firebase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseSignInViewModel(): ViewModel() {

    var isSigningIn: Boolean = false

    var userId = MutableLiveData<String?>()

    init{
        val id = Firebase.auth.currentUser?.uid
        if( id != null){
            userId.value = id
        }
    }

    fun setUserId(id: String?){
        userId.value = id
    }
}