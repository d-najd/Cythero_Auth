package com.tradiebot.cythero.presentation.components.dialogs

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.tradiebot.cythero.R
import com.tradiebot.cythero.presentation.components.CytheroMultipurposeMenu
import com.tradiebot.cythero.presentation.util.show
import com.tradiebot.cythero.util.CytheroDateFormat
import java.util.*

/**
 * @sample DatePickerDialogPreview
 */
@SuppressLint("ComposableNaming")
//suppressed because the dialog is converted to sort of a composable
@Composable
fun DatePickerDialog(
    @StringRes title: Int,
    select: Date? = null,
    bounds: CalendarConstraints? = null,
    onDateSelected: (Date) -> Unit = {},
): MaterialDatePicker<Long> {
    val datePicker = remember {
        MaterialDatePicker.Builder.datePicker()
            .setSelection(select?.time ?: Date().time)
            .setCalendarConstraints(bounds)
            .setTitleText(title)
            .build()
    }

    DisposableEffect(datePicker) {
        val listener = MaterialPickerOnPositiveButtonClickListener<Long> {
            if (it != null) onDateSelected(Date(it))
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
@Composable
fun DatePickerDialogPreview(){
    val dateFormat = CytheroDateFormat.defaultDateFormat()
    var date by remember { mutableStateOf(Date()) }
    val datePicker = DatePickerDialog(
        select = date,
        title = R.string.action_select_part,
        onDateSelected = { date = it },
    )

    CytheroMultipurposeMenu(
        title = stringResource(R.string.info_select_date_range),
        text = dateFormat.format(date),
        onClick = {
            datePicker.show()
        }
    )
}
