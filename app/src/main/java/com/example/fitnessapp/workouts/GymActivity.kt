package com.example.fitnessapp.workouts

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentDataType
import androidx.compose.ui.autofill.ContentDataType.Companion.Text
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
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

import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import kotlinx.coroutines.delay

class GymActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymApp()
        }
    }
}
@RequiresApi(Build.VERSION_CODES.P)
@Composable
@Preview
fun GymApp() {
    val context = LocalContext.current
    val formattedTime = "00:00:00" // replace later with real state
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
    var started by remember { mutableStateOf(false) }
    var startedGif by remember { mutableStateOf(false) }
    var startedStopWatchBtns by remember { mutableStateOf(false) }
    var counter = 1
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
                    .padding(top = 90.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.timercircleset),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .align(Alignment.Center)
                        .size(250.dp), // shortcut for width & height
                )
                Button(
                    onClick = {
                        stopWatch.start()
                        if (!started && !startedGif) {
                            SoundPlayer.playPop(context)
                        }
                        // show the Row after this
                        started = true
                        startedGif = true
                        startedStopWatchBtns = true
                    },
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(250.dp)
                        .alpha(0F),
                ) { }
            }
        }
    }
    Box(
        modifier = Modifier
            .padding(top = 126.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        // Infinite transition keeps running
        val infiniteTransition = rememberInfiniteTransition()

        // Animate radius value
        val radius by infiniteTransition.animateFloat(
            initialValue = 290f,
            targetValue = 150f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
        // Play sound once per second when conditions are true
        LaunchedEffect(started, startedGif) {
            if (started && startedGif) {
                while (true) {
                    SoundPlayer.playPop(context) // play your pop sound
                    delay(1000) // wait 1 second before next sound
                }
            }
        }
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .align(Alignment.Center),
        ){
            Canvas(
                modifier = Modifier
                    .size(200.dp)
                    .alpha(if(started && startedGif)1f else 0f),
            ) {
                drawCircle(
                    brush = Brush.verticalGradient(   // gradient fill
                        colors = listOf(Color.Cyan, Color.Blue)
                    ),
                    radius = radius,
                    center = center
                )
            }
        }
        // Timer Text-------------------------------------------------------------------------------
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
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 370.dp)
    ) {
        Box() {
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.stopwatch_pause),
                    contentDescription = null,
                    modifier = Modifier
                        .alpha(if (startedStopWatchBtns) 1f else 0f)
                        .size(50.dp), // shortcut for width & height
                )
                Image(
                    painter = painterResource(id = R.drawable.stopwatch_stop),
                    contentDescription = null,
                    modifier = Modifier
                        .alpha(if (startedStopWatchBtns) 1f else 0f)
                        .size(50.dp), // shortcut for width & height
                )
            }
            // Show Row only if stopwatch started
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                Arrangement.Center
            ) {
                Button(
                    onClick = { stopWatch.pause(); startedGif = false },
                    modifier = Modifier
                        .size(50.dp)
                        .alpha(0F),
                ) { }
                Button(
                    onClick = {
                        stopWatch.reset(); startedGif = false; startedStopWatchBtns = false
                    },
                    modifier = Modifier
                        .size(50.dp)
                        .alpha(0F),
                ) { }
            }
        }
        Spacer(Modifier.height(20.dp))
    }
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 420.dp)
    ) {
        // SetTime Container ----------------------------------------------------------------
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth(),
        ){
            // Body Images
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.wrapContentWidth(),
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
            val testList = listOf<String>("00:01", "00:02", "00:03")
            // Body Text
            Column(
                modifier = Modifier.wrapContentWidth()
                    .padding(top = 15.dp)
                    .padding(start = 65.dp),
            ) {
                Spacer(Modifier.height(0.dp))
                stopWatch.formattedEndTimeList.forEachIndexed { index, endTime ->
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .wrapContentWidth(),
                    ) {
                        Text(
                            text = "Set Time: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color(0xFF4F2912)
                        )
                        Text(
                            text = endTime,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color(0xFF4F2912)
                        )
                    }
                    if (index != stopWatch.formattedEndTimeList.size - 1) {
                        Spacer(Modifier.height(48.dp))
                    }
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
