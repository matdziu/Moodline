package com.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.common.constants.MAX_CHAR_LENGTH_OF_DIARY_ENTRY
import com.designsystem.R
import com.designsystem.theme.MoodlineTheme
import com.designsystem.theme.customColors

@Composable
fun DiaryListItem(
    modifier: Modifier = Modifier,
    entryText: String,
    emotionSymbol: EmotionSymbol,
    formattedDate: String,
    onEdit: () -> Unit,
    onRemove: () -> Unit,
) {
    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var isRemovalConfirmationDialogVisible by rememberSaveable {
        mutableStateOf(false)
    }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.elevatedCardColors().copy(
            containerColor = MaterialTheme.colorScheme.onPrimary,
        ),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    val emotionImageModifier = Modifier.size(60.dp)
                    when (emotionSymbol) {
                        EmotionSymbol.Awful -> AwfulEmotionImage(modifier = emotionImageModifier)
                        EmotionSymbol.Bad -> BadEmotionImage(modifier = emotionImageModifier)
                        EmotionSymbol.Good -> GoodEmotionImage(modifier = emotionImageModifier)
                        EmotionSymbol.Meh -> MehEmotionImage(modifier = emotionImageModifier)
                        EmotionSymbol.Rad -> RadEmotionImage(modifier = emotionImageModifier)
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    val emotionText = stringResource(
                        id = when (emotionSymbol) {
                            EmotionSymbol.Awful -> R.string.emotion_symbol_awful_text
                            EmotionSymbol.Bad -> R.string.emotion_symbol_bad_text
                            EmotionSymbol.Good -> R.string.emotion_symbol_good_text
                            EmotionSymbol.Meh -> R.string.emotion_symbol_meh_text
                            EmotionSymbol.Rad -> R.string.emotion_symbol_rad_text
                        }
                    )
                    val emotionColor = when (emotionSymbol) {
                        EmotionSymbol.Awful -> customColors.awfulEmotionColor
                        EmotionSymbol.Bad -> customColors.badEmotionColor
                        EmotionSymbol.Good -> customColors.goodEmotionColor
                        EmotionSymbol.Meh -> customColors.mehEmotionColor
                        EmotionSymbol.Rad -> customColors.radEmotionColor
                    }
                    Column {
                        Text(
                            text = formattedDate,
                            style = MaterialTheme.typography.labelSmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Start,
                        )
                        Text(
                            text = emotionText,
                            style = MaterialTheme.typography.displayMedium,
                            color = emotionColor,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = entryText.take(MAX_CHAR_LENGTH_OF_DIARY_ENTRY),
                    textAlign = TextAlign.Start,
                )
            }
            IconButton(
                onClick = { isContextMenuVisible = true },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
            ) {
                Icon(Icons.Filled.MoreVert, contentDescription = "")
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 16.dp, end = 40.dp)
            ) {
                DropdownMenu(
                    expanded = isContextMenuVisible,
                    onDismissRequest = { isContextMenuVisible = false },
                ) {
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = R.string.diary_list_item_edit)) },
                        onClick = {
                            isContextMenuVisible = false
                            onEdit()
                        },
                    )
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = R.string.diary_list_item_remove)) },
                        onClick = {
                            isContextMenuVisible = false
                            isRemovalConfirmationDialogVisible = true
                        },
                    )
                }
            }

            if (isRemovalConfirmationDialogVisible) {
                RemovalConfirmationDialog(
                    onConfirm = {
                        isRemovalConfirmationDialogVisible = false
                        onRemove()
                    },
                    onDismiss = { isRemovalConfirmationDialogVisible = false },
                )
            }
        }
    }
}

@Composable
private fun RemovalConfirmationDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(icon = { Icon(Icons.Filled.Delete, contentDescription = null) },
        title = {
            Text(
                text = stringResource(id = R.string.diary_removal_confirmation_title),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            MoodlineOutlinedButton(
                text = stringResource(id = R.string.diary_removal_confirmation_positive_button),
                onClick = onConfirm,
                color = MaterialTheme.colorScheme.error
            )
        },
        dismissButton = {
            MoodlineOutlinedButton(
                text = stringResource(id = R.string.diary_removal_confirmation_negative_button),
                onClick = onDismiss
            )
        })
}

@Preview
@Composable
private fun DiaryItemPreview() {
    MoodlineTheme {
        DiaryListItem(
            entryText = "Lorem ipsum bla bla bla",
            emotionSymbol = EmotionSymbol.Rad,
            formattedDate = "10:30, 22nd March 2024",
            onEdit = {},
            onRemove = {},
        )
    }
}