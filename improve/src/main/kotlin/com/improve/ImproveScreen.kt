package com.improve

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Face2
import androidx.compose.material.icons.filled.Face4
import androidx.compose.material.icons.filled.Face5
import androidx.compose.material.icons.filled.Face6
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.common.constants.IMPROVE_AD_REF_LINK
import com.designsystem.components.MoodlineButton
import com.designsystem.theme.MoodlineTheme

@Composable
internal fun ImproveRoute(
    onBackButtonPressed: () -> Unit,
    improveViewModel: ImproveViewModel = hiltViewModel(),
) {
    BackHandler {
        onBackButtonPressed()
    }
    ImproveScreen(
        onLearnMoreButtonClicked = improveViewModel::trackRefLinkClick,
    )
}

@Composable
internal fun ImproveScreen(
    onLearnMoreButtonClicked: () -> Unit,
) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Icon(
                imageVector = Icons.Filled.Face2,
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
            Icon(
                imageVector = Icons.Filled.Face,
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
            Icon(
                imageVector = Icons.Filled.Face5,
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
            Icon(
                imageVector = Icons.Filled.Face4,
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
            Icon(
                imageVector = Icons.Filled.Face6,
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
        }
        Spacer(modifier = Modifier.height(22.dp))
        Text(
            text = stringResource(id = R.string.ad_title),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(22.dp))
        MoodlineButton(
            text = stringResource(id = R.string.ad_cta_text),
            onClick = {
                onLearnMoreButtonClicked()
                uriHandler.openUri(IMPROVE_AD_REF_LINK)
            },
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ImproveScreenPreview() {
    MoodlineTheme {
        ImproveScreen(
            onLearnMoreButtonClicked = {},
        )
    }
}