package com.example.fitnessapp.workouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp.R
import kotlin.math.max
import kotlin.math.min

class WorkoutSessionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                WorkoutSessionScreen(onBack = { finish() })
            }
        }
    }
}

@Composable
private fun WorkoutSessionScreen(onBack: () -> Unit) {
    var hasStarted by remember { mutableStateOf(false) }
    var completedSets by remember { mutableIntStateOf(1) }
    var xpEarned by remember { mutableIntStateOf(260) }
    var exercisesDone by remember { mutableIntStateOf(3) }
    var setsDoneTotal by remember { mutableIntStateOf(13) }
    var minutesRemaining by remember { mutableIntStateOf(25) }

    val totalSets = 4
    val totalExercises = 5
    val targetXp = 300
    val totalSessionSets = 20
    val activeSet = min(totalSets, completedSets + 1)
    val actionLabel = when {
        completedSets >= totalSets -> "Workout Complete"
        !hasStarted -> "Start Workout"
        else -> "Complete Set"
    }

    val screenColor = Color(0xFFF4D0C2)
    val textColor = Color(0xFF582C1F)
    val cardColor = Color(0xFFEEDFD5)
    val borderColor = Color(0xFFF16D63)
    val actionColor = Color(0xFFEF8C6E)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = screenColor
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 420.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = textColor)
                    }
                    Text(
                        text = "Bench Press",
                        color = textColor,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                Text(
                    text = "Set $activeSet of $totalSets",
                    color = textColor,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 2.dp)
                )

                RepsRing(
                    progress = completedSets.toFloat() / totalSets.toFloat(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                )

                Button(
                    onClick = {
                        if (!hasStarted) {
                            hasStarted = true
                            return@Button
                        }

                        if (completedSets < totalSets) {
                            completedSets += 1
                            xpEarned = min(targetXp, xpEarned + 20)
                            setsDoneTotal = min(totalSessionSets, setsDoneTotal + 1)
                            exercisesDone = min(totalExercises, exercisesDone + 1)
                            minutesRemaining = max(0, minutesRemaining - 3)
                        }
                    },
                    enabled = completedSets < totalSets,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = actionColor,
                        contentColor = Color.White,
                        disabledContainerColor = actionColor.copy(alpha = 0.6f)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = actionLabel,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                WorkoutXpCard(
                    xpEarned = xpEarned,
                    targetXp = targetXp,
                    cardColor = cardColor,
                    borderColor = borderColor
                )

                FriendsCard(cardColor = cardColor, borderColor = borderColor)

                SessionProgressCard(
                    exercisesDone = exercisesDone,
                    totalExercises = totalExercises,
                    setsDone = setsDoneTotal,
                    totalSets = totalSessionSets,
                    minutesRemaining = minutesRemaining,
                    xpLeft = max(0, targetXp - xpEarned),
                    cardColor = cardColor,
                    borderColor = borderColor,
                    textColor = textColor
                )
            }
        }
    }
}

@Composable
private fun RepsRing(progress: Float, modifier: Modifier = Modifier) {
    val stroke = 16.dp
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(320.dp)
                .padding(top = 8.dp)
        ) {
            val arcStart = 180f
            val arcSweep = 180f
            drawArc(
                color = Color(0xFFFFAE9A),
                startAngle = arcStart,
                sweepAngle = arcSweep,
                useCenter = false,
                style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = Color(0xFFB7F07F),
                startAngle = arcStart,
                sweepAngle = arcSweep * progress.coerceIn(0f, 1f),
                useCenter = false,
                style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round)
            )
        }

        Box(
            modifier = Modifier
                .size(230.dp)
                .clip(RoundedCornerShape(120.dp))
                .border(1.dp, Color(0xFFF16D63), RoundedCornerShape(120.dp))
                .background(Color(0xFFF0BDB0)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "8",
                    color = Color(0xFF582C1F),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 54.sp
                )
                Text(
                    text = "Target: 8 reps",
                    color = Color(0xFF582C1F),
                    fontSize = 18.sp
                )
                Text(
                    text = "@ 60 KG",
                    color = Color(0xFF582C1F),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun WorkoutXpCard(xpEarned: Int, targetXp: Int, cardColor: Color, borderColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(cardColor)
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .height(46.dp)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.leaf_vector),
            contentDescription = "XP",
            tint = Color.Unspecified,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = " +40 xp",
            color = Color(0xFF582C1F),
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
        Text(
            text = "Level 5",
            color = Color(0xFF8A6E61),
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 18.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "$xpEarned/$targetXp",
            color = Color(0xFF582C1F),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun FriendsCard(cardColor: Color, borderColor: Color) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(cardColor)
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = "Friends",
            color = Color(0xFF582C1F),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(text = "Anna completed a Gym workout", color = Color(0xFF6E4B3F), fontSize = 13.sp)
        Text(text = "Ben took a morning walk", color = Color(0xFF6E4B3F), fontSize = 13.sp)
    }
}

@Composable
private fun SessionProgressCard(
    exercisesDone: Int,
    totalExercises: Int,
    setsDone: Int,
    totalSets: Int,
    minutesRemaining: Int,
    xpLeft: Int,
    cardColor: Color,
    borderColor: Color,
    textColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(cardColor)
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Session Progress",
                color = textColor,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = ">", color = textColor, fontSize = 20.sp, fontWeight = FontWeight.Normal)
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Exercises", color = textColor, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "$exercisesDone / $totalExercises completed", color = textColor, fontSize = 13.sp)
        }
        LinearProgressIndicator(
            progress = { exercisesDone.toFloat() / totalExercises.toFloat() },
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),
            color = Color(0xFFFA9A45),
            trackColor = Color(0xFFFFDCC5)
        )
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Sets", color = textColor, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "$setsDone / $totalSets done", color = textColor, fontSize = 13.sp)
        }
        LinearProgressIndicator(
            progress = { setsDone.toFloat() / totalSets.toFloat() },
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),
            color = Color(0xFFFA9A45),
            trackColor = Color(0xFFFFDCC5)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.time_vector),
                contentDescription = "Time left",
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = " ~${minutesRemaining} min remaining",
                color = textColor,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 4.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.leaf_vector),
                contentDescription = "XP left",
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = " +${xpLeft} xp",
                color = Color(0xFF8EA47F),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
                )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, widthDp = 393, heightDp = 852)
@Composable
private fun WorkoutSessionScreenPreview() {
    MaterialTheme {
        WorkoutSessionScreen(onBack = {})
    }
}
