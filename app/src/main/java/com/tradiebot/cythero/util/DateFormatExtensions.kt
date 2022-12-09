package com.tradiebot.cythero.util

import java.text.SimpleDateFormat
import java.util.*


class CytheroDateFormat(){
    companion object {
        fun defaultDateFormat(): SimpleDateFormat {
            return SimpleDateFormat(DEFAULT_APP_DATE_FORMAT, Locale.getDefault())
        }

        fun defaultRequestDateFormat(): SimpleDateFormat {
            return SimpleDateFormat(DEFAULT_API_DATE_FORMAT, Locale.getDefault())
        }

        /** for displaying data */
        private const val DEFAULT_APP_DATE_FORMAT = "d MMM yyyy"
        /** for parsing data from the api */
        private const val DEFAULT_API_DATE_FORMAT = "yyyy-MM-d HH:mm:ss"
    }
}

