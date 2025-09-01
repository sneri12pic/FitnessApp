package com.example.fitnessapp.workouts

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp.ExerciseActivity
import com.example.fitnessapp.HomeActivity
import com.example.fitnessapp.ProfileActivity
import com.example.fitnessapp.R
import com.example.fitnessapp.SettingsActivity
import com.example.fitnessapp.util.SoundPlayer
import kotlinx.coroutines.delay


import androidx.compose.material3.*
import androidx.compose.ui.text.TextStyle


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
    // Column for stopwatch + set time inputs
    val stopWatch = remember { StopWatch() }
    var startedStopWatchBtns by remember { mutableStateOf(false) }
    var showRest by remember { mutableStateOf(false) }
    val restTimer = remember { RestTimer() }

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
    // Stop Watch-----------------------------------------------------------------------------------
    if (!showRest) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // StopWatch Container -----------------------------------------------------------------
            var started by remember { mutableStateOf(false) }
            var startedGif by remember { mutableStateOf(false) }
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
                            painter = painterResource(id = R.drawable.stopwatchsetcircle),
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
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(200.dp)
                            .alpha(if (started && startedGif) 1f else 0f),
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
                    //val formatted = restTimer.remainingTime.collectAsState().value
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
                            onClick = {
                                stopWatch.pause()
                                startedGif = false
                            },
                            modifier = Modifier
                                .size(50.dp)
                                .alpha(0F),
                        ) { }
                        Button(
                            onClick = {
                                stopWatch.reset()
                                startedGif = false
                                startedStopWatchBtns = false
                                showRest = true
                            },
                            modifier = Modifier
                                .size(50.dp)
                                .alpha(0F),
                        ) { }
                    }
                }
                Spacer(Modifier.height(20.dp))
            }
            // Set Times Container
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 420.dp)
            ) {
                // SetTime Container
                Box(
                    modifier = Modifier
                        .wrapContentHeight()
                        .wrapContentWidth(),
                ) {
                    // Body Images
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.wrapContentWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Addition of variables to wrapHeight without additional 35dp in the end
                            var times = 4
                            var counter = 1
                            repeat(4) {
                                Image(
                                    painter = painterResource(id = R.drawable.set_time_body),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(300.dp),
                                )
                                if (counter < times) {
                                    Spacer(modifier = Modifier.height(15.dp))
                                }
                                counter++
                            }
                        }
                        Column(
                            modifier = Modifier.wrapContentWidth()
                                .align(Alignment.TopEnd)
                                .padding(top = 10.dp)
                                .padding(end = 60.dp),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.repsbody),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(30.dp)
                                    .wrapContentWidth()
                            )
                            Spacer(modifier = Modifier.height(35.dp))
                            // Addition of variables to wrapHeight without additional 35dp in the end
                            var times = 3
                            var counter = 1
                            repeat(times) {
                                Image(
                                    painter = painterResource(id = R.drawable.repsbody),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(30.dp)
                                        .wrapContentWidth()
                                )
                                if (counter < times) {
                                    Spacer(modifier = Modifier.height(35.dp))
                                }
                                counter++
                            }
                        }
                        Column(
                            modifier = Modifier.wrapContentWidth()
                                .align(Alignment.TopEnd)
                                .padding(top = 10.dp)
                                .padding(end = 140.dp),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.moodbody),
                                contentDescription = null,
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .width(55.dp)
                            )
                            Spacer(modifier = Modifier.height(35.dp))
                            // Addition of variables to wrapHeight without additional 35dp in the end
                            var times = 3
                            var counter = 1
                            repeat(times) {
                                Image(
                                    painter = painterResource(id = R.drawable.moodbody),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .wrapContentHeight()
                                        .width(55.dp)
                                )
                                var text by remember { mutableStateOf("") }
                                if (counter < times) {
                                    Spacer(modifier = Modifier.height(35.dp))
                                }
                                counter++
                            }
                        }
                        /*var text by remember { mutableStateOf("") }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 50.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            TextField(
                                modifier = Modifier
                                    .height(15.dp)
                                    .width(50.dp),
                                value = text,
                                onValueChange = { text = it },
                                label = { Text("") },
                                placeholder = { Text("Reps") },
                                textStyle = TextStyle(fontSize = 16.sp),
                                singleLine = true
                            )
                        }*/
                    }
                    val testList = listOf<String>("00:01", "00:02", "00:03", "00:04")

                    val testTimerList = listOf<String>("120", "60", "180", "180")

                    // Body Text StopWatch
                    Column(
                        modifier = Modifier.wrapContentWidth()
                            .padding(top = 8.dp)
                            .padding(start = 65.dp),
                    ) {
                        //stopWatch.formattedEndTimeList.forEachIndexed { index, endTime ->
                        stopWatch.formattedEndTimeList.forEachIndexed { index, endTime ->
                            Row(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .wrapContentWidth(),
                            ) {
                                Text(
                                    text = "Set   ",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = Color(0xFF4F2912)
                                )
                                Text(
                                    text = "" + index + " ",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = Color(0, 255, 0)
                                )
                                Text(
                                    text = ": ",
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
                    // Body Text RestTimer
                    Column(
                        modifier = Modifier.wrapContentWidth()
                            .padding(top = 28.dp)
                            .padding(start = 65.dp),
                    ) {
                        restTimer.restTimesList.forEachIndexed { index, restTime ->
                            Row(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .wrapContentWidth(),
                            ) {
                                Text(
                                    text = "Rest ",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = Color(0xFF4F2912)
                                )
                                Text(
                                    text = "" + index + " ",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = Color(0, 255, 0)
                                )
                                Text(
                                    text = ": ",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = Color(0xFF4F2912)
                                )
                                Text(
                                    text = restTime,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = Color(0xFF4F2912)
                                )
                            }
                            if (index != restTimer.restTimesList.size - 1) {
                                Spacer(Modifier.height(46.dp))
                            }
                        }
                    }

                }
            }
        }
    }
    // Rest Timer-----------------------------------------------------------------------------------
    else{

        var expanded by remember { mutableStateOf(false) }
        val remainingTime by restTimer.remainingTime.collectAsState()
        var isStopped by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 0.dp)
                    .wrapContentHeight(),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.timersetcircle),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .align(Alignment.Center)
                        .size(250.dp), // shortcut for width & height
                )
                Button(
                    onClick = {
                        // Value of isRunning is false by default
                        if(restTimer.isRunning.value){ restTimer.startTimer(120) }
                        else{ restTimer.stopTimer(); isStopped = true; showRest = false }
                    },
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .align(Alignment.Center)
                        .size(250.dp)
                        .alpha(0F),
                ) { }
                Box(Modifier.align(Alignment.Center)){
                    Text(
                        text = remainingTime?.let { restTimer.formatTime(it) } ?: "- - : - -",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color(0xFF4F2912),
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterEnd)
                ) {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = "More options",
                            Modifier.size(30.dp)
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        restTimer.timerList.forEach { seconds ->
                            DropdownMenuItem(
                                text = { Text(restTimer.formatTime(seconds)) },
                                onClick = {
                                    expanded = false
                                    restTimer.startTimer(seconds)
                                }
                            )
                        }
                    }
                }
                Box(modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 425.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ballwitharrow),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 0.dp)
                            .align(Alignment.Center)
                            .size(70.dp), // shortcut for width & height
                    )
                    Button(
                        onClick = {
                            showRest = false
                        },
                        modifier = Modifier
                            .padding(top = 0.dp)
                            .align(Alignment.Center)
                            .size(80.dp)
                            .alpha(0F),
                    ) { }
                }
            }
        }
        /*// Hide rest screen when timer is done
        LaunchedEffect(remainingTime) {
            if (remainingTime == 0L) {
                showRest = false // only leave the rest screen when the flag is true
            }
        }*/


        if(remainingTime == 0L && isStopped){
            showRest = false // only leave the rest screen when the flag is true
            isStopped = false
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
