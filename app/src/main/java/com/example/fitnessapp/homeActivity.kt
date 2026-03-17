package com.example.fitnessapp.workouts

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp.ExerciseActivity
import com.example.fitnessapp.ProfileActivity
import com.example.fitnessapp.R
import kotlin.math.roundToInt

class homeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewHomeScreen(
                onStartWorkoutClick = {
                    startActivity(Intent(this, WorkoutSessionActivity::class.java))
                },
                onStepViewClick = {
                    startActivity(Intent(this, ProfileActivity::class.java))
                },
                onLogActivityClick = {
                    startActivity(Intent(this, ExerciseActivity::class.java))
                },
                onWorkoutTimeClick = {
                    startActivity(Intent(this, WorkoutSessionActivity::class.java))
                },
                onRecentClick = {
                    startActivity(Intent(this, ProfileActivity::class.java))
                }
            )
        }
    }
}

private val ScreenColor = Color(0xFFF8D4C5)
private val CardColor = Color(0xFFFEEADC)
private val BorderColor = Color(0x80F07167)
private val HeaderColor = Color(0xFF72473A)
private val BodyColor = Color(0xFF7D635A)
private val RankColor = Color(0xFFE1AD2D)
private val ActionButtonColor = Color(0xFFF8C6B5)
private val AccentColor = Color(0xFFF07167)
private val ProgressTrackColor = Color(0xFFA58D86)

@Composable
fun NewHomeScreen(
    onStartWorkoutClick: () -> Unit = {},
    onStepViewClick: () -> Unit = {},
    onLogActivityClick: () -> Unit = {},
    onWorkoutTimeClick: () -> Unit = {},
    onRecentClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = ScreenColor
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val reservedBottomHeight = maxHeight * 0.15f

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(start = 20.dp, end = 20.dp, top = 30.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                GreetingHeader()
                StartWorkoutButton(onClick = onStartWorkoutClick)
                TodaysProgressCard(
                    onStepViewClick = onStepViewClick,
                    onLogActivityClick = onLogActivityClick
                )
                StreakCard()
                WorkoutTimeCard(onClick = onWorkoutTimeClick)
                TeamChallengeCard()
                RecentCard(onClick = onRecentClick)
                Spacer(modifier = Modifier.height(reservedBottomHeight))
            }
        }
    }
}

@Composable
private fun GreetingHeader() {
    Column(modifier = Modifier.padding(start = 6.dp, bottom = 6.dp)) {
        Text(
            text = "Good afternoon",
            color = Color(0xFF4F2912),
            fontSize = 42.sp,
            lineHeight = 42.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Here's your progress for today.",
            color = Color(0xFF5E3A2D),
            fontSize = 16.sp,
            lineHeight = 18.sp
        )
    }
}

@Composable
private fun StartWorkoutButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFEF8C6E),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = "Start Workout",
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun TodaysProgressCard(
    onStepViewClick: () -> Unit,
    onLogActivityClick: () -> Unit
) {
    val progress = 0.68f
    val steps = 7532

    HomeCard {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Today's Progress",
                        color = HeaderColor,
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = String.format("%,d steps", steps),
                        color = BodyColor,
                        fontSize = 14.sp
                    )
                }
                ProgressCircle(progress = progress)
            }

            ProgressBar(progress = progress)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ProgressActionButton(
                    label = "Step View",
                    iconRes = R.drawable.new_home_steps_icon,
                    onClick = onStepViewClick,
                    modifier = Modifier.weight(1f)
                )
                ProgressActionButton(
                    label = "Log Activity",
                    iconRes = R.drawable.new_home_log_activity_icon,
                    onClick = onLogActivityClick,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun ProgressCircle(progress: Float) {
    val progressPercent = (progress.coerceIn(0f, 1f) * 100).roundToInt()

    Box(
        modifier = Modifier.size(54.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                brush = Brush.verticalGradient(
                    listOf(
                        AccentColor.copy(alpha = 0.5f),
                        Color(0xFF8A413B).copy(alpha = 0.5f)
                    )
                )
            )
        }
        Box(
            modifier = Modifier
                .size(49.dp)
                .clip(CircleShape)
                .background(Color(0xFFF4CEC0)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$progressPercent%",
                color = HeaderColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun ProgressBar(progress: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(ProgressTrackColor.copy(alpha = 0.7f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress.coerceIn(0f, 1f))
                .height(4.dp)
                .background(AccentColor)
        )
    }
}

@Composable
private fun ProgressActionButton(
    label: String,
    iconRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(ActionButtonColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = label,
            color = HeaderColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun WorkoutTimeCard(onClick: () -> Unit) {
    HomeCard(modifier = Modifier.clickable(onClick = onClick)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Workout time",
                    color = HeaderColor,
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Gym Time: 2hr 30 min",
                    color = BodyColor,
                    fontSize = 14.sp
                )
            }

            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF8C6B5))
                    .padding(6.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.new_home_workout_time_image),
                    contentDescription = "Workout machine",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = ">",
                color = HeaderColor,
                fontSize = 28.sp,
                lineHeight = 28.sp
            )
        }
    }
}

@Composable
private fun StreakCard() {
    HomeCard {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(id = R.drawable.new_home_streak_idle),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                        .padding(end = 6.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.new_home_streak_idle),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                        .padding(end = 6.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.new_home_streak_done),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                        .padding(end = 6.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.new_home_streak_done),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                        .padding(end = 6.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.new_home_streak_idle),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                        .padding(end = 6.dp)
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(
                    text = "Streak!",
                    color = HeaderColor,
                    fontSize = 18.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "3 of 4 planned sessions completed",
                    color = BodyColor,
                    fontSize = 14.sp,
                    lineHeight = 10.sp
                )
            }
        }
    }
}

@Composable
private fun TeamChallengeCard() {
    HomeCard {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Team challenge",
                    color = HeaderColor,
                    fontSize = 18.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "2 days left",
                    color = BodyColor,
                    fontSize = 10.sp
                )
            }

            TeamScoreRow(
                rank = "1st",
                totalScore = "10000",
                chartRes = R.drawable.new_home_team_chart_1
            )
            TeamScoreRow(
                rank = "3rd",
                totalScore = "300",
                chartRes = R.drawable.new_home_team_chart_2
            )
        }
    }
}

@Composable
private fun TeamScoreRow(rank: String, totalScore: String, chartRes: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = rank,
            color = RankColor,
            fontSize = 16.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(42.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "with total of\nscore of $totalScore",
            color = HeaderColor,
            fontSize = 11.sp,
            lineHeight = 13.sp,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Image(
            painter = painterResource(id = chartRes),
            contentDescription = null,
            modifier = Modifier
                .width(177.dp)
                .height(39.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
private fun RecentCard(onClick: () -> Unit) {
    HomeCard(modifier = Modifier.clickable(onClick = onClick)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    fontWeight = FontWeight.SemiBold,
                    text = "Recent: Yesterday",
                    color = HeaderColor,
                    fontSize = 12.sp
                )
                Text(
                    text = "...",
                    color = HeaderColor,
                    fontSize = 18.sp,
                    lineHeight = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Gym Session: 2hr 15min (New Bench Press Record : 100 KG)",
                color = BodyColor,
                fontSize = 10.sp
            )
            Text(
                text = "Total exercise done: 10",
                color = BodyColor,
                fontSize = 10.sp
            )
            Text(
                text = "Muscles Groups: Legs, Chest, Arms",
                color = BodyColor,
                fontSize = 10.sp
            )
        }
    }
}

@Composable
private fun HomeCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(CardColor)
            .border(1.dp, BorderColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        content()
    }
}

@Preview(showBackground = true, widthDp = 380, heightDp = 840)
@Composable
private fun NewHomeScreenPreview() {
    MaterialTheme {
        NewHomeScreen()
    }
}
