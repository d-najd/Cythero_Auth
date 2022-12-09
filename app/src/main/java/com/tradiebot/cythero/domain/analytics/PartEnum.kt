package com.tradiebot.cythero.domain.analytics

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName
import com.tradiebot.cythero.R

enum class PartEnum(@StringRes val nameId: Int){
    @SerializedName("Fender")
    FENDER(R.string.field_part_fender),
    @SerializedName("Hood")
    HOOD(R.string.field_part_hood),
    @SerializedName("Roof Tile")
    ROOF_TILE(R.string.field_part_roof_tile),
    @SerializedName("Door")
    DOOR(R.string.field_part_door),
    @SerializedName("Tail Wing")
    TAIL_WING(R.string.field_part_tail_wing),
    @SerializedName("Wing")
    WING(R.string.field_part_wing),
    @SerializedName("Propeller")
    PROPELLER(R.string.field_part_propeller),
    @SerializedName("Beam")
    BEAM(R.string.field_part_beam),
    @SerializedName("Rail Section")
    RAIL_SECTION(R.string.field_part_rail_section),
}
