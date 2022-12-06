package com.tradiebot.cythero.util

import java.text.SimpleDateFormat
import java.util.*


class CytheroDateFormat(){
    companion object {
        fun defaultDateFormat(): SimpleDateFormat {
            return SimpleDateFormat(DEFAULT_CYTHERO_DATE_FORMAT, Locale.getDefault())
        }

        private const val DEFAULT_CYTHERO_DATE_FORMAT = "d MMM yyyy"
    }
}

