package com.tradiebot.cythero.domain.analytics.user.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.io.Serializable


data class AnalyticsUser(
    @SerializedName("calculated_data") val calculatedData: AnalyticsUserCalculated,
    @SerializedName("table") val analyticsUserTable: AnalyticsUserTable,
): Serializable {
    companion object{
        fun mockInstance(): Map<Long, AnalyticsUser> {
            val analyticsUserType = object : TypeToken<Map<Long, AnalyticsUser>>() {}.type
            return Gson().fromJson(MOCK_USER_ANALYTICS, analyticsUserType)
        }
    }
}

@Suppress("unused")
private const val MOCK_USER_ANALYTICS =
            "{\n" +
            "  \"4\": {\n" +
            "    \"calculated_data\": {\n" +
            "      \"Average Paint\": 193.6571052631579,\n" +
            "      \"Average Time\": 2.65478947368421,\n" +
            "      \"Least Paint\": 3.995,\n" +
            "      \"Longest Time\": 17.817,\n" +
            "      \"Most Paint\": 576.694,\n" +
            "      \"Shortest Time\": 0.7,\n" +
            "      \"User IDs\": [\n" +
            "        4\n" +
            "      ]\n" +
            "    },\n" +
            "    \"table\": {\n" +
            "      \"Base Good Coverage\": [\n" +
            "        30.15193,\n" +
            "        9.803279,\n" +
            "        22.84156,\n" +
            "        11.89198,\n" +
            "        62.33812\n" +
            "      ],\n" +
            "      \"Base High Coverage\": [\n" +
            "        69.762,\n" +
            "        90.1973,\n" +
            "        45.56386,\n" +
            "        88.1086,\n" +
            "        6.805035\n" +
            "      ],\n" +
            "      \"Base Low Coverage\": [\n" +
            "        0.08665106,\n" +
            "        0,\n" +
            "        5.012044,\n" +
            "        0,\n" +
            "        30.85744\n" +
            "      ],\n" +
            "      \"Clear Coat Used (Milliliters)\": [\n" +
            "        36.57087,\n" +
            "        46.32558,\n" +
            "        24.33184,\n" +
            "        47.25103,\n" +
            "        19.52573\n" +
            "      ],\n" +
            "      \"Clear Good Coverage\": [\n" +
            "        95.7336,\n" +
            "        55.46868,\n" +
            "        81.3482,\n" +
            "        84.42623,\n" +
            "        69.31967\n" +
            "      ],\n" +
            "      \"Clear High Coverage\": [\n" +
            "        4.266394,\n" +
            "        44.42886,\n" +
            "        0.5681881,\n" +
            "        15.55913,\n" +
            "        2.238876\n" +
            "      ],\n" +
            "      \"Clear Low Coverage\": [\n" +
            "        0.0005854801,\n" +
            "        0.1030445,\n" +
            "        9.320728,\n" +
            "        0.01522248,\n" +
            "        28.44204\n" +
            "      ],\n" +
            "      \"Color Used (Milliliters)\": [\n" +
            "        74.65383,\n" +
            "        80.16724,\n" +
            "        57.1335,\n" +
            "        98.17004,\n" +
            "        20.52419\n" +
            "      ],\n" +
            "      \"Grade\": [\n" +
            "        \"B\",\n" +
            "        \"C\",\n" +
            "        \"B\",\n" +
            "        \"B\",\n" +
            "        \"A\",\n" +
            "        \"A\"\n" +
            "      ],\n" +
            "      \"Part\": [\n" +
            "        \"Fender\",\n" +
            "        \"Fender\",\n" +
            "        \"Hood\",\n" +
            "        \"Fender\",\n" +
            "        \"Fender\"\n" +
            "      ],\n" +
            "      \"Primer Good Coverage\": [\n" +
            "        85.68443,\n" +
            "        60.69789,\n" +
            "        66.90955,\n" +
            "        62.04558,\n" +
            "        47.99063\n" +
            "      ],\n" +
            "      \"Primer High Coverage\": [\n" +
            "        14.31616,\n" +
            "        39.30269,\n" +
            "        32.94849,\n" +
            "        37.25628,\n" +
            "        0,\n" +
            "      ],\n" +
            "      \"Primer Low Coverage\": [\n" +
            "        0,\n" +
            "        0,\n" +
            "        0.1430339,\n" +
            "        0.6987273,\n" +
            "        52.00995\n" +
            "      ],\n" +
            "      \"Primer Used (Milliliters)\": [\n" +
            "        51.90266,\n" +
            "        48.8667,\n" +
            "        74.54537,\n" +
            "        58.86859,\n" +
            "        19.0883\n" +
            "      ],\n" +
            "      \"Session ID\": [\n" +
            "        \"0OeU52LdgCv\",\n" +
            "        \"25Lpkx6hNUI\",\n" +
            "        \"43CMPFgkbt2\",\n" +
            "        \"4qYf03dFlOT\",\n" +
            "        \"4ums7PTFGa3\"\n" +
            "      ],\n" +
            "      \"Total Paint Used\": [\n" +
            "        163.127,\n" +
            "        175.36,\n" +
            "        156.011,\n" +
            "        204.29,\n" +
            "        59.138\n" +
            "      ],\n" +
            "      \"Total Paint Used (Milliliters)\": [\n" +
            "        163.127,\n" +
            "        175.36,\n" +
            "        156.011,\n" +
            "        204.29,\n" +
            "        59.138\n" +
            "      ],\n" +
            "      \"Total Time Played\": [\n" +
            "        120,\n" +
            "        91,\n" +
            "        120,\n" +
            "        116,\n" +
            "        52\n" +
            "      ],\n" +
            "      \"Total Time Spent\": [\n" +
            "        2.067,\n" +
            "        1.683,\n" +
            "        2.133,\n" +
            "        1.983,\n" +
            "        0.967\n" +
            "      ],\n" +
            "      \"User ID\": [\n" +
            "        4,\n" +
            "        4,\n" +
            "        4,\n" +
            "        4,\n" +
            "        4\n" +
            "      ]\n" +
            "    }\n" +
            "  }\n" +
            "}"