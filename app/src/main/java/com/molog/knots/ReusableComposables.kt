package com.molog.knots

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.molog.knots.ui.theme.fbFontFamily
import com.molog.knots.ui.theme.knotsPrimary

object ReusableComposables {
    fun myLoaderDialogue(context: Context): ProgressDialog{
        return ProgressDialog(context).apply {
            setMessage("Loading, please wait...")
            setTitle("Knots")
            setIcon(R.drawable.knotslogo)
            setCancelable(false) // Disable cancelling
        }
    }
    fun myToast(context: Context, message:String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    @Composable
    fun KnotsTextButton(text: String, onClick: () -> Unit){
        TextButton(onClick = onClick) {
            Text(text, fontWeight = FontWeight.Light, fontFamily = fbFontFamily)
        }
    }
    @Composable
    fun KnotsButtonElevated(text:String, modifier: Modifier = Modifier, onClick:()->Unit) {
        ElevatedButton(onClick = onClick,
            modifier = modifier.fillMaxWidth(),
            colors = ButtonDefaults.elevatedButtonColors(
                contentColor = knotsPrimary,
                disabledContentColor = Color.Gray
            )
        ) {
            Text(text, fontWeight = FontWeight.Bold,
                fontSize = 20.sp, fontFamily = fbFontFamily)
        }
    }
    @Composable
    fun KnotsButton(
        text: String,
        modifier: Modifier = Modifier,
        onClick: () -> Unit,
        enabled: Boolean = true
    ) {
        Button(onClick = onClick,
            modifier = modifier.fillMaxWidth(),
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = knotsPrimary,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            )
        ) {
            Text(text, fontWeight = FontWeight.Bold,
                fontSize = 20.sp, fontFamily = fbFontFamily)
        }
    }
}