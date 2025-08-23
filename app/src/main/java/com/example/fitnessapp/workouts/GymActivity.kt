package com.example.fitnessapp.workouts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentDataType
import androidx.compose.ui.autofill.ContentDataType.Companion.Text
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.graphics.alpha
import androidx.room.util.TableInfo
import com.example.fitnessapp.ExerciseActivity
import com.example.fitnessapp.HomeActivity
import com.example.fitnessapp.ProfileActivity
import com.example.fitnessapp.R
import com.example.fitnessapp.SettingsActivity
import com.example.fitnessapp.util.SoundPlayer

class GymActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymApp()
        }
    }
}
@Composable
@Preview
fun GymApp() {
    val context = LocalContext.current
    val formattedTime = "00:00" // replace later with real state
    // Column for stopwatch + set time inputs
    val stopWatch = remember { StopWatch() }
    // Background Image --------------------------------------------------------------------
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop // scales and crops to fill the container
        )
    }

    // StopWatch Container -----------------------------------------------------------------
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 100.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
            ){
                Image(
                    painter = painterResource(id = R.drawable.timercircleset),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(250.dp), // shortcut for width & height
                )
                Button(
                    onClick = {stopWatch.start(); SoundPlayer.playPop(context)},
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(250.dp)
                        .alpha(0F),
                ) { }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stopWatch.formattedTime,
                        fontWeight = FontWeight.Bold,
                        fontSize = 40.sp,
                        color = Color(0xFF4F2912)
                    )
                }
            }
        }
        Spacer(Modifier.height(30.dp))
        Box(){
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                Arrangement.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.stopwatch_pause),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp), // shortcut for width & height
                )
                Image(
                    painter = painterResource(id = R.drawable.stopwatch_stop),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp), // shortcut for width & height
                )
            }
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                Arrangement.Center
            ) {
                Button(
                    onClick = {stopWatch.pause()},
                    modifier = Modifier
                        .size(50.dp)
                        .alpha(0F),
                ) { }
                Button(
                    onClick = {stopWatch.reset()},
                    modifier = Modifier
                        .size(50.dp)
                        .alpha(0F),
                ) { }
            }
        }
        Spacer(Modifier.height(20.dp))
        // SetTime Container ----------------------------------------------------------------
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                repeat(3) {
                    Image(
                        painter = painterResource(id = R.drawable.set_time_body),
                        contentDescription = null,
                        modifier = Modifier
                            .height(50.dp)
                            .width(300.dp),
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }
    }

    // BottomPanel Container ---------------------------------------------------------------
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Bottom Panel Image
        Image(
            painter = painterResource(id = R.drawable.bottompanelworkouts),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(95.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
        )
        // Horizontal Buttons Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(top = 10.dp)
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Start
        ) {
            Spacer(modifier = Modifier.width(95.dp))
            Button(
                onClick = {
                    val intent = Intent(context, HomeActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .size(40.dp)
                    .alpha(0f) // invisible, like in XML
            ) {}

            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {
                    val intent = Intent(context, ExerciseActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .size(50.dp)
                    .alpha(0f)
            ) {}

            Spacer(modifier = Modifier.width(15.dp))
            Button(
                onClick = {
                    val intent = Intent(context, ProfileActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .size(40.dp)
                    .alpha(0f)
            ) {}

            Spacer(modifier = Modifier.width(15.dp))
            Button(
                onClick = {
                    val intent = Intent(context, SettingsActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .size(40.dp)
                    .alpha(0f)
            ) {}
        }
    }
}
