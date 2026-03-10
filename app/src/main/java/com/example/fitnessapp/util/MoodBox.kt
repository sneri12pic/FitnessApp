// MoodBox.kt
package com.example.fitnessapp.util

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MoodBox(
    percent: Float,
    label: String,
    modifier: Modifier = Modifier
) {
    // Clamp percentage
    val p = percent.coerceIn(0f, 100f)

    // Pick gradient colors
    val colors = when {
        p <= 25f -> listOf(Color(0xFFFF6B6B), Color(0xFFFFFF6B)) // Red → Yellow
        p <= 50f -> listOf(Color(0xFFFFFF6B), Color(0xFF6BFF7C)) // Yellow → Green
        p <= 75f -> listOf(Color(0xFF6BFF7C), Color(0xFF6BC6FF)) // Green → Blue
        else -> listOf(Color(0xFF6BC6FF), Color(0xFF6BC6FF)) // Solid Blue
    }

    Box(
        modifier = modifier
            .width(120.dp)
            .height(60.dp)
            .background(
                brush = Brush.linearGradient(colors),
                shape = RoundedCornerShape(12.dp)
            )
            .border(1.dp, Color(0xFF704A3E), RoundedCornerShape(12.dp))
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = label,
                color = Color.Black,
                fontSize = 16.sp
            )
        }
    }
}
