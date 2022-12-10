package com.tradiebot.cythero.presentation.components.charts

import android.graphics.Canvas
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColor
import androidx.core.graphics.toColorLong
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.*
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.Utils
import com.github.mikephil.charting.utils.ViewPortHandler
import logcat.logcat


@Composable
fun ComposeLegend(
    modifier: Modifier = Modifier,
    legend: Legend,
){

    LegendVerticalAlignment.CENTER
    Column(
        modifier = modifier,
        verticalArrangement = legend.verticalAlignment.toComposeArrangement(),
        horizontalAlignment = legend.horizontalAlignment.toComposeAlignment(),
    ) {
        for(entry in legend.entries) {
            ComposeLegendEntry(
                legend = legend,
                entry = entry
            )
        }
    }
}

@Composable
fun ComposeLegendEntry(
    legend: Legend,
    entry: LegendEntry,
){
    Row(
        modifier = Modifier
            .padding(10.dp)
    ) {

        Column(
            modifier = Modifier
                .scale(.1f)
                .width((entry.formSize / 2).dp * 10)
                .height((entry.formSize / 2).dp * 10),
        ) {
            when (if (entry.form == LegendForm.DEFAULT) legend.form else entry.form) {
                LegendForm.CIRCLE -> {
                    Canvas(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {

                        drawCircle(
                            color = Color(entry.formColor),
                            radius = (entry.formSize/2) * 10,
                        )
                    }
                }
                LegendForm.DEFAULT -> {
                    throw IllegalStateException()
                }
                LegendForm.LINE -> {
                    Divider()
                }
                LegendForm.SQUARE -> {
                    Box(
                        modifier = Modifier
                            .background(Color(entry.formColor))
                            .fillMaxSize(),
                    )
                }
                else -> {}
            }
        }

        Column(
            modifier = Modifier.height((entry.formSize/2).dp * 10),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = entry.label,
                textAlign = TextAlign.End
            )
        }
    }
}

fun LegendVerticalAlignment.toComposeArrangement(): Arrangement.Vertical = when(this){
    LegendVerticalAlignment.TOP -> Arrangement.Top
    LegendVerticalAlignment.CENTER -> Arrangement.Center
    LegendVerticalAlignment.BOTTOM -> Arrangement.Bottom
}

fun LegendHorizontalAlignment.toComposeAlignment(): Alignment.Horizontal = when(this){
    LegendHorizontalAlignment.LEFT -> Alignment.Start
    LegendHorizontalAlignment.CENTER -> Alignment.CenterHorizontally
    LegendHorizontalAlignment.RIGHT -> Alignment.End
}