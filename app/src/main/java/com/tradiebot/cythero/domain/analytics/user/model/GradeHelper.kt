package com.tradiebot.cythero.domain.analytics.user.model

import androidx.annotation.StringRes
import com.tradiebot.cythero.R

enum class GradeHelper(val rgb: String, @StringRes val shortName: Int){
    A("#00A83D", R.string.field_grade_a),
    B("#0078AA", R.string.field_grade_b),
    C("#D3212C", R.string.field_grade_c),
    NAN("#AAAAAA", R.string.field_grade_nan) // used for stuff like no coverage
}