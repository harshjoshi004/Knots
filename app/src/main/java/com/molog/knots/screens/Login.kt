package com.molog.knots.screens

import android.app.ProgressDialog
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.molog.knots.AuthViewModel
import com.molog.knots.R
import com.molog.knots.ReusableComposables
import com.molog.knots.ui.theme.fbFontFamily
import com.molog.knots.ui.theme.knotsBlack
import com.molog.knots.ui.theme.knotsPrimary

@Composable
fun Login(navController: NavHostController, wrapUp:()->Unit) {
    val context = LocalContext.current
    val authViewModel: AuthViewModel = viewModel()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    val progressDialog = ReusableComposables.myLoaderDialogue(context = context)

    Box(modifier = Modifier
        .background(knotsBlack)
        .fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(modifier = Modifier.animateContentSize()) {
                //Enter Email
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = if (it.isValidEmail()) "" else "Invalid email format"
                    },
                    label = { Text("Email") },
                    isError = emailError.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                )
                if (emailError.isNotEmpty()) {
                    Text(
                        emailError,
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.animateContentSize()) {
                //Enter Password
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError =
                            if (it.length >= 8) "" else "Password must be at least 8 characters"
                    },
                    label = { Text("Password") },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image =
                            if (isPasswordVisible) R.drawable.visible else R.drawable.invisible
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(painter = painterResource(id = image), contentDescription = null)
                        }
                    },
                    isError = passwordError.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                )
                if (passwordError.isNotEmpty()) {
                    Text(
                        passwordError,
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            //Button to Login
            Button(
                onClick = {
                    progressDialog.show()
                    if(isDetailValid(password = password, email = email,
                            passwordError = passwordError, emailError = emailError,
                            context = context)
                    ){
                        authViewModel.loginWithFirebase(email,password,
                            onSuccess = {
                                wrapUp()
                                progressDialog.hide()
                                ReusableComposables.myToast(context, "SignIn Successful!")
                            },
                            onError = {
                                progressDialog.hide()
                                ReusableComposables.myToast(
                                    context,
                                    authViewModel.firebaseError.value?:"Something went wrong!"
                                )
                            }
                        )
                    }
                },
                enabled = emailError.isEmpty() && passwordError.isEmpty(),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = knotsPrimary,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.White
                )
            ) {
                Text("Login", fontWeight = FontWeight.Bold, fontSize = 20.sp, fontFamily = fbFontFamily)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = {
                    navController.navigate("register") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            ) {
                Text("New User? Create Account",
                    fontWeight = FontWeight.Light,
                    fontFamily = fbFontFamily
                )
            }
        }
    }
}

// Helper function to validate email format
fun String.isValidEmail() = android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
