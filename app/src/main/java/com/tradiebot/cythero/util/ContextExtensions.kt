@file:Suppress("unused")

package com.tradiebot.cythero.util

import android.content.Context
import android.util.DisplayMetrics
import com.tradiebot.cythero.app.util.view.ContextHolder
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

/** gets app context */
fun mAppContext(): Context {
    return Injekt.get<ContextHolder>().getContext()
}

/**
 * This method converts dp unit to equivalent pixels, depending on device density.
 *
 * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
 * @return A float value to represent px equivalent to dp depending on device density
 */
fun Context.convertDpToPixel(dp: Float): Float {
    return dp * (resources
        .displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

/**
 * This method converts device specific pixels to density independent pixels.
 *
 * @param px A value in px (pixels) unit. Which we need to convert into db
 * @return A float value to represent dp equivalent to px value
 */
fun Context.convertPixelsToDp(px: Float): Float {
    return px / (resources
        .displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}
