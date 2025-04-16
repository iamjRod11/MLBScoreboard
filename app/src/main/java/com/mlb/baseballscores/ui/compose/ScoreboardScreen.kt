package com.mlb.baseballscores.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.mlb.baseballscores.data.models.Competition
import com.mlb.baseballscores.data.models.Competitor
import com.mlb.baseballscores.data.models.Event
import com.mlb.baseballscores.data.models.Odds
import com.mlb.baseballscores.data.models.Probable
import com.mlb.baseballscores.data.models.Scoreboard
import com.mlb.baseballscores.ui.mvvm.viewmodel.ScoreboardViewModel

@Composable
fun ScoreboardScreen(viewModel: ScoreboardViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    with(uiState) {
        if (isLoading) {
            ProgressBar()
        }
        scoreboardData?.let {
            ScoreboardDetails(
                scoreboard = it,
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun ProgressBar() {
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ScoreboardDetails(
    scoreboard: Scoreboard,
    modifier: Modifier = Modifier,
    viewModel: ScoreboardViewModel
) {
    LazyColumn(
        modifier = modifier
            .background(MaterialTheme.colorScheme.onBackground)
            .fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(scoreboard.events.size) { index ->
            ScoreboardEventCard(event = scoreboard.events[index], viewModel = viewModel)
        }
    }
}

@Composable
fun ScoreboardEventCard(
    event: Event,
    modifier: Modifier = Modifier,
    viewModel: ScoreboardViewModel
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 32.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Event header: title and formatted date
            Text(
                text = event.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(12.dp))
            // Render each competition within the event
            event.competitions.forEachIndexed { index, competition ->
                CompetitionDetails(competition = competition, viewModel = viewModel)
                if (index != event.competitions.lastIndex) {
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
    }
}

@Composable
fun CompetitionDetails(
    competition: Competition,
    modifier: Modifier = Modifier,
    viewModel: ScoreboardViewModel
) {
    Column(modifier = modifier.fillMaxWidth()) {
        val gameNotStarted = competition.status?.type?.name == "STATUS_SCHEDULED"
        if (gameNotStarted) {
            Text(
                text = "Start time: " + viewModel.formatDate(competition.startDate),
                style = MaterialTheme.typography.titleMedium,
                color = Color.Red
            )
        } else {
            Row {
                Text(
                    text = "${competition.status?.type?.detail}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Red
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.padding(start = 8.dp, end = 20.dp),
                    text = "R          H          E",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Spacer(modifier = Modifier.height(8.dp))
        // List each competitor within the competition
        competition.competitors.forEach { competitor ->
            CompetitorDetails(competitor = competitor, showProbable = gameNotStarted)
            Spacer(modifier = Modifier.height(8.dp))
        }
        // If odds data is available, offer a toggle to show/hide odds.
        if (competition.odds.isNotEmpty()) {
            var showOdds by remember { mutableStateOf(true) }
            TextButton(onClick = { showOdds = !showOdds }) {
                Text(text = if (showOdds) "Hide Odds" else "Show Odds")
            }
            if (showOdds) {
                OddsDetails(oddsList = competition.odds.filterNotNull())
            }
        }
    }
}

@Composable
fun CompetitorDetails(
    modifier: Modifier = Modifier,
    competitor: Competitor?,
    showProbable: Boolean = false,
) {
    competitor?.let { comp ->
        Column(modifier = modifier.fillMaxWidth()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Display team logo if available using Coil's AsyncImage.
                Box(
                    modifier = Modifier
                        .background(
                            color = if (isSystemInDarkTheme()) {
                                MaterialTheme.colorScheme.onSurface
                            } else {
                                Color.Transparent
                            }
                        )
                        .border(
                            width = 1.dp,
                            color = if (isSystemInDarkTheme()) {
                                MaterialTheme.colorScheme.onSurface
                            } else {
                                Color.Transparent
                            },
                            shape = CircleShape
                        )
                ) {
                    AsyncImage(
                        model = comp.team?.logo,
                        contentDescription = "${comp.team?.name} logo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .padding(8.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = comp.team?.name.orEmpty(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "(${comp.homeAway})",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.weight(1f))


                if (!showProbable) {
                    val scoreDisplay = if (Integer.valueOf(comp.score) >= 10) {
                        comp.score
                    } else {
                        " ${comp.score} "
                    }
                    val hitsDisplay = if (comp.hits >= 10) {
                        comp.hits.toString()
                    } else {
                        " ${comp.hits} "
                    }
                    val errorsDisplay = if (comp.errors >= 10) {
                        comp.errors.toString()
                    } else {
                        " ${comp.errors} "
                    }
                    val gridItems = listOf(scoreDisplay, hitsDisplay, errorsDisplay)

                    LazyHorizontalGrid(
                        modifier = Modifier.height(60.dp),
                        rows = GridCells.Fixed(1),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(8.dp),
                    ) {
                        items(gridItems.size) { index ->
                            Box(modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    shape = RoundedCornerShape(4.dp)
                                )
                            ) {
                                Text(
                                    modifier = Modifier.padding(8.dp),
                                    text = gridItems[index],
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
            // Show probable starters details if present
            if (comp.probables.isNotEmpty() && showProbable) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Probable Starters:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Medium
                )
                comp.probables.forEach { probable ->
                    ProbableStarterDetails(probable = probable)
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProbableStarterDetails(
    probable: Probable,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(start = 16.dp, top = 4.dp, bottom = 4.dp)) {
        probable.athlete?.let {
            Text(
                text = it.fullName,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Text(
            text = "Record: ${probable.record}",
            style = MaterialTheme.typography.bodySmall
        )
        if (probable.statistics.isNotEmpty()) {
            Spacer(modifier = Modifier.height(2.dp))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                probable.statistics.forEach { stat ->
                    StatChip(statName = stat.name, displayValue = stat.displayValue)
                }
            }
        }
    }
}

@Composable
fun StatChip(
    statName: String,
    displayValue: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
        modifier = modifier
    ) {
        Text(
            text = "$statName: $displayValue",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun OddsDetails(
    oddsList: List<Odds>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        oddsList.forEach { odds ->
            // Display basic odds information.
            Text(
                text = "Provider: ${odds.provider?.name}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Odds: ${odds.details}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
            // If you need to show additional odds fields (e.g., over/under, spread), add them here.
        }
    }
}

@Composable
fun ScoreDetails(competitors: List<Competitor>) {
    val gridCells = listOf<String>(
        "R", "H", "E",
        competitors[0].score, competitors[0].hits.toString(), competitors[0].errors.toString(),
        competitors[1].score, competitors[1].hits.toString(), competitors[1].errors.toString()
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(gridCells.size) { index ->
            Box {
                Text(
                    text = gridCells[index],
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(
                    text = gridCells[index]
                )
            }
        }
    }
}

// Helper function to format date strings.
// In a production app, use a proper date formatter.
fun formatEventDate(dateString: String): String = dateString