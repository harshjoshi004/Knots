package com.molog.knots

import android.content.Context
import android.net.Uri
import android.service.autofill.UserData
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.storage
import com.molog.knots.models.UserModel
import com.molog.knots.utils.SharedPrefs
import java.util.UUID

class AuthViewModel: ViewModel() {
    //Firebase service instances
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()

    private val storageRef = Firebase.storage.reference
    private val imageRef = storageRef.child("users/${UUID.randomUUID()}.jpg")

    //Database Reference(equivalent to directory)
    val userRef = database.getReference("users")

    //LiveData to present to UI
    private val _firebaseUser = MutableLiveData<FirebaseUser>()
    val firebaseUser:LiveData<FirebaseUser> = _firebaseUser

    private val _firebaseError = MutableLiveData<String>()
    val firebaseError:LiveData<String> = _firebaseError

    //init function
    init {
        _firebaseUser.value = auth.currentUser
    }

    //login fun
    fun loginWithFirebase(
        email: String,
        password: String,
        onSuccess: () -> Unit = {},
        onError: () -> Unit = {}
    ){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{ task->
            if (task.isSuccessful) {
                _firebaseUser.postValue(auth.currentUser)
                Log.d("LoginWithFirebase", "Login successful: ${auth.currentUser?.email}")
                onSuccess()
            } else {
                _firebaseError.value = handleFirebaseAuthException(task.exception)
                Log.d("LoginWithFirebase", "Login failed: ${handleFirebaseAuthException(task.exception)}")
                onError()
            }
        }
    }

    //register fun
    fun registerWithFirebase(
        email:String,
        password:String,
        name:String,
        bio:String,
        imageUri: Uri,
        userName: String,
        uid:String?,
        onSuccess: () -> Unit = {},
        onError: () -> Unit = {},
        context: Context
    ){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task->
            if(task.isSuccessful){
                _firebaseUser.postValue(auth.currentUser)
                Log.d("SignUpWithFirebase", "SignUp successful: ${auth.currentUser?.email}")
                onSuccess()
                saveImage(email, password, name, bio, userName, imageUri, auth.currentUser?.uid, context)
            }else{
                _firebaseError.value = handleFirebaseAuthException(task.exception)
                Log.d("SignUpWithFirebase", "SignUp failed: ${handleFirebaseAuthException(task.exception)}")
                onError()
            }
        }
    }

    private fun saveImage(
        email: String,
        password: String,
        name: String,
        bio: String,
        userName:String,
        imageUri: Uri,
        uid: String?,
        context: Context
    ) {
        val uploadTask = imageRef.putFile(imageUri)
        uploadTask.addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener {
                saveData(email, password, name, bio, userName, it.toString(), uid, context)
            }
        }
    }

    private fun saveData(
        email: String,
        password: String,
        name: String,
        bio: String,
        userName: String,
        toString: String,
        uid: String?,
        context: Context
    ){
        val userData = UserModel(email, password, name, bio, userName, toString)
        userRef.child(uid!!).setValue(userData)
            .addOnSuccessListener {
                Log.d("SaveData", "Data saved successfully $userData")
                SharedPrefs.storeData(userData, context)
            }.addOnFailureListener {
                Log.d("SaveData", "Data could not be saved")
            }
    }
}

fun handleFirebaseAuthException(exception: Exception?):String {
    return when (exception) {
        is FirebaseAuthInvalidCredentialsException -> {
            "The email or password is incorrect!"
        }
        is FirebaseAuthInvalidUserException -> {
            "No account found with this email!"
        }
        is FirebaseAuthUserCollisionException -> {
            "An account already exists with this email!"
        }
        is FirebaseNetworkException -> {
            "Please check your internet connection!"
        }
        else -> {
            "Unknown error occurred!"
        }
    }
}