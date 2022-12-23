package com.tradiebot.cythero.domain.analytics

import androidx.annotation.StringRes
import com.tradiebot.cythero.R

enum class CoverageType(@StringRes val nameId: Int): java.io.Serializable {
    OVERALL(R.string.field_coverage_type_overall),
    PRIMER(R.string.field_coverage_type_primer),
    BASE(R.string.field_coverage_type_base),
    CLEAR(R.string.field_coverage_type_clear),
}