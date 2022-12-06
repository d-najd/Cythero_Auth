package com.tradiebot.cythero.presentation.components

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.util.view.FragmentManagerHolder
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun rememberDatePickerDialog(
    @StringRes title: Int = R.string.action_select_part,
    select: Date? = null,
    bounds: CalendarConstraints? = null,
    onDateSelected: (Date) -> Unit = {},
): MaterialDatePicker<Long> {
    val datePicker = remember {
        MaterialDatePicker.Builder.datePicker()
            //.setSelection((select?.time
             //   ?: Date().time) + 24.hours.toLong(DurationUnit.MILLISECONDS))
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


@SuppressLint("RestrictedApi")
@Composable
fun CytheroDatePicker(
    modifier: Modifier = Modifier,
){
    val DATE_FORMAT = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    var date by remember {
        mutableStateOf(TextFieldValue(DATE_FORMAT.format(Date())))
    }
    val fragmentManager = Injekt.get<FragmentManagerHolder>().fragmentManager

    val datePicker = rememberDatePickerDialog(
        select = DATE_FORMAT.parse(date.text),
        title = R.string.action_select_part,
    ) { date = TextFieldValue(DATE_FORMAT.format(it)) }


    IconButton(onClick = { datePicker.show(fragmentManager, "Date")}) {
        Icon(Icons.Default.DateRange,contentDescription = null)
    }
}