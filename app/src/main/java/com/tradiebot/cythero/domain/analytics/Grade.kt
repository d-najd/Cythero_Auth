package com.tradiebot.cythero.domain.analytics

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName
import com.tradiebot.cythero.R
import java.io.Serializable

enum class Grade(val rgb: String, @StringRes val nameId: Int): Serializable {
    @SerializedName("A")
    A("#00A83D", R.string.field_grade_a),
    @SerializedName("B")
    B("#0078AA", R.string.field_grade_b),
    @SerializedName("C")
    C("#D3212C", R.string.field_grade_c),
    NAN("#AAAAAA", R.string.field_grade_nan) // used for stuff like no coverage
}