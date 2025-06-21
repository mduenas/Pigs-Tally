package com.markduenas.pigstally2.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.markduenas.pigstally2.ui.theme.PassThePigsColors

@Composable
fun RulesDialog(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = PassThePigsColors.Background
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Pass the Pigs Rules",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = PassThePigsColors.Primary
                    )
                    
                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close rules",
                            tint = PassThePigsColors.OnBackground
                        )
                    }
                }
                
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 16.dp),
                    color = PassThePigsColors.Primary.copy(alpha = 0.3f)
                )
                
                // Scrollable content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Objective
                    RuleSection(
                        title = "🎯 Objective",
                        content = "Be the first player to reach 100 points by rolling pig dice and scoring based on how they land."
                    )
                    
                    // Basic Gameplay
                    RuleSection(
                        title = "🎮 Basic Gameplay",
                        content = "• Players take turns rolling two pig dice\n" +
                                "• Score points based on how the pigs land\n" +
                                "• After each roll, choose to BANK your points or ROLL AGAIN\n" +
                                "• Banking adds your turn total to your permanent score\n" +
                                "• Rolling again risks losing all points earned this turn"
                    )
                    
                    // Scoring Positions
                    RuleSection(
                        title = "📊 Scoring Positions",
                        content = "**Individual Pig Positions:**\n" +
                                "• Trotter (standing upright): 5 points\n" +
                                "• Razorback (lying on back): 5 points\n" +
                                "• Snouter (standing on snout): 10 points\n" +
                                "• Leaning Jowler (leaning on side): 15 points\n" +
                                "• Sider (lying on side): 0 points individually\n\n" +
                                "**Two Pig Combinations:**\n" +
                                "• Both same side (Sider): 1 point total\n" +
                                "• Mixed positions: Add individual scores"
                    )
                    
                    // Double Bonuses
                    RuleSection(
                        title = "✨ Double Bonuses",
                        content = "When both pigs land in the same scoring position, you get bonus points!\n\n" +
                                "• Double Trotter: 20 points (5+5+10 bonus)\n" +
                                "• Double Razorback: 20 points (5+5+10 bonus)\n" +
                                "• Double Snouter: 40 points (10+10+20 bonus)\n" +
                                "• Double Leaning Jowler: 60 points (15+15+30 bonus)"
                    )
                    
                    // Penalties
                    RuleSection(
                        title = "⚠️ Penalties",
                        content = "**Pig Out** (opposite sides):\n" +
                                "• One pig dot up, one pig dot down\n" +
                                "• Lose all points earned this turn\n" +
                                "• Turn ends immediately\n\n" +
                                "**Oinker** (both pigs touching):\n" +
                                "• Reset your TOTAL score to 0\n" +
                                "• Turn ends immediately\n\n" +
                                "**Piggyback** (one pig on top of other):\n" +
                                "• You are eliminated from the game\n" +
                                "• Cannot play anymore"
                    )
                    
                    // Strategy Tips
                    RuleSection(
                        title = "💡 Strategy Tips",
                        content = "• Banking early keeps your points safe\n" +
                                "• Higher scores are rarer but more rewarding\n" +
                                "• Consider your total score when deciding to risk another roll\n" +
                                "• Watch out for penalties - they can end your game!\n" +
                                "• The game combines luck with risk management"
                    )
                    
                    // Winning
                    RuleSection(
                        title = "🏆 Winning",
                        content = "The first player to reach 100 points (or the target score set in settings) wins the game immediately when they bank their points."
                    )
                }
                
                // Close button at bottom
                Spacer(modifier = Modifier.height(16.dp))
                
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PassThePigsColors.Primary
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Got it!",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
private fun RuleSection(
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = PassThePigsColors.Primary
        )
        
        Text(
            text = content,
            fontSize = 14.sp,
            color = PassThePigsColors.OnBackground,
            lineHeight = 20.sp
        )
    }
}