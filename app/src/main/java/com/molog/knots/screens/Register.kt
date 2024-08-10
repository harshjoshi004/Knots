package com.molog.knots.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.molog.knots.R
import com.molog.knots.ui.theme.fbFontFamily
import com.molog.knots.ui.theme.knotsBlack
import com.molog.knots.ui.theme.knotsPrimary

@Composable
fun Register(navController: NavHostController, wrapUp:()->Unit) {
    var name by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var nameError by remember { mutableStateOf("") }
    var bioError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    Box(modifier = Modifier.background(knotsBlack).fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Profile Image
            Image(
                painter = painterResource(id = R.drawable.defimg),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.animateContentSize()) {
                // Enter Name
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        nameError = if (it.isNotBlank()) "" else "Name cannot be empty"
                    },
                    label = { Text("Name") },
                    isError = nameError.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                )
                if (nameError.isNotEmpty()) {
                    Text(
                        nameError,
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.animateContentSize()) {
                // Enter Bio
                OutlinedTextField(
                    value = bio,
                    onValueChange = {
                        bio = it
                        bioError = if (it.isNotBlank()) "" else "Bio cannot be empty"
                    },
                    label = { Text("Bio") },
                    isError = bioError.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                )
                if (bioError.isNotEmpty()) {
                    Text(
                        bioError,
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.animateContentSize()) {
                // Enter Email
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
                // Enter Password
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

            Spacer(modifier = Modifier.height(16.dp))

            // Button to Register
            Button(
                onClick = {
                    // Perform registration action (not yet implemented)
                    wrapUp()
                },
                enabled = nameError.isEmpty() && bioError.isEmpty() && emailError.isEmpty() && passwordError.isEmpty(),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = knotsPrimary,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.White
                )
            ) {
                Text("Register", fontWeight = FontWeight.Bold, fontSize = 20.sp, fontFamily = fbFontFamily)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = { navController.navigate("login") }) {
                Text("Already have an account? Log In", fontWeight = FontWeight.Light, fontFamily = fbFontFamily)
            }
        }
    }
}