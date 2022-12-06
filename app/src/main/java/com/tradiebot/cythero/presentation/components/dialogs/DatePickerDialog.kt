package com.tradiebot.cythero.presentation.components.dialogs

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.tradiebot.cythero.R
import com.tradiebot.cythero.presentation.components.CytheroMultipurposeMenu
import com.tradiebot.cythero.presentation.util.show
import com.tradiebot.cythero.util.CytheroDateFormat
import java.util.*

/**
 * @see [DatePickerDialogPreview]
 */
@SuppressLint("ComposableNaming")
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
    var date by remember { mutableStateOf(TextFieldValue(dateFormat.format(Date()))) }
    val datePicker = DatePickerDialog(
        select = dateFormat.parse(date.text),
        title = R.string.action_select_part,
        onDateSelected = { date = TextFieldValue(dateFormat.format(it)) },
    )

    CytheroMultipurposeMenu(
        title = stringResource(R.string.info_select_date_range),
        text = date.text,
        onClick = {
            datePicker.show()
        }
    )
}
