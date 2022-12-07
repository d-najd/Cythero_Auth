package com.tradiebot.cythero.presentation.components.dialogs

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.util.toAndroidXPair
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.tradiebot.cythero.R
import com.tradiebot.cythero.presentation.components.CytheroMultipurposeMenu
import com.tradiebot.cythero.presentation.util.show
import com.tradiebot.cythero.util.CytheroDateFormat
import java.util.*
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime

/**
 * @see [DateRangePickerDialogPreview]
 */
@SuppressLint("ComposableNaming")
@OptIn(ExperimentalTime::class)
@Composable
fun DateRangePickerDialog(
    @StringRes title: Int,
    select: Pair<Date?, Date?>? = null,
    bounds: CalendarConstraints? = null,
    onDateSelected: (Date, Date) -> Unit = { _, _ -> },
): MaterialDatePicker<androidx.core.util.Pair<Long, Long>> {
    val formattedSelection = Pair(
        first = select?.first?.time ?: (Date().time - Duration.Companion.convert(
            24.0,
            DurationUnit.HOURS,
            DurationUnit.MILLISECONDS
        ).toLong()),
        second = (select?.second?.time ?: Date().time)
    ).toAndroidXPair()

    val datePicker = remember {
        MaterialDatePicker.Builder.dateRangePicker()
            .setSelection(formattedSelection)
            .setCalendarConstraints(bounds)
            .setTitleText(title)
            .build()
    }

    DisposableEffect(datePicker) {
        val listener =
            MaterialPickerOnPositiveButtonClickListener<androidx.core.util.Pair<Long, Long>> {
                if (it != null)
                    onDateSelected(Date(it.first), Date(it.second))
            }
        datePicker.addOnPositiveButtonClickListener(listener)
        onDispose {
            datePicker.removeOnPositiveButtonClickListener(listener)
        }
    }

    return datePicker
}

/**
 * Example code for date picker dialog, the code can't be previewed because it uses dark injekt magic
 * but calling the function somewhere else (without preview) will work
 */
// @Preview
@OptIn(ExperimentalTime::class)
@Composable
fun DateRangePickerDialogPreview(){
    val dateFormat = CytheroDateFormat.defaultDateFormat()
    var dateRange by remember { mutableStateOf(Pair(
        first = Date(
            Date().time -
                    Duration.Companion.convert(168.0, DurationUnit.HOURS, DurationUnit.MILLISECONDS).toLong()
        ),
        second = Date()
    )) }

    val dateRangePicker = DateRangePickerDialog(
        select = Pair(
            first = dateRange.first,
            second = dateRange.second
        ),
        title = R.string.info_select_date_range,
        onDateSelected = { f, s -> dateRange = Pair(f,s) }
    )

    CytheroMultipurposeMenu(
        modifier = Modifier
            .padding(top = 20.dp),
        title = stringResource(R.string.info_select_date_range),
        text = "${dateFormat.format(dateRange.first)} - ${dateFormat.format(dateRange.second)}",
        onClick = {
            dateRangePicker.show()
        }
    )
}
