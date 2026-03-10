package com.example.fitnessapp.workouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp.R

class homeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewHomeScreen()
        }
    }
}

private val ScreenColor = Color(0xFFFFF6F0)
private val CardColor = Color(0xFFFEEADC)
private val BorderColor = Color(0x80F07167)
private val HeaderColor = Color(0xFF72473A)
private val BodyColor = Color(0xFF7D635A)
private val RankColor = Color(0xFFE1AD2D)

@Composable
fun NewHomeScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = ScreenColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 12.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            WorkoutTimeCard()
            StreakCard()
            TeamChallengeCard()
            RecentCard()
        }
    }
}

@Composable
private fun WorkoutTimeCard() {
    HomeCard {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Workout time",
                    color = HeaderColor,
                    fontSize = 16.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Gym Time: 2hr 30 min",
                    color = BodyColor,
                    fontSize = 10.sp
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
                    fontSize = 10.sp,
                    lineHeight = 12.sp
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.new_home_streak_idle),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.new_home_streak_idle),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.new_home_streak_done),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.new_home_streak_done),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.new_home_streak_idle),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
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
private fun RecentCard() {
    HomeCard {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
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
private fun HomeCard(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(CardColor)
            .border(1.dp, BorderColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 10.dp)
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
