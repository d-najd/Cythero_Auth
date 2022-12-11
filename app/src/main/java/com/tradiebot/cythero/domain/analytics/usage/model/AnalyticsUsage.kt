package com.tradiebot.cythero.domain.analytics.usage.model


import android.provider.Telephony.Mms.Part
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.util.Date

data class AnalyticsUsageHolder(
    @SerializedName("table") val analyticsUsage: AnalyticsUsage
)

data class AnalyticsUsage(
    @SerializedName("Date") val date: List<Date>,
    @SerializedName("Paint Used (Ml)") val paintUsedMl: List<Double>,
    @SerializedName("Part") val part: List<Part>,
    @SerializedName("Session ID") val sessionID: List<String>,
    @SerializedName("Total Time Spent (min)") val totalTimeSpentMin: List<Double>,
    @SerializedName("User") val user: List<String>
) {
    companion object {
        fun mockInstance(): AnalyticsUsage {
            return Injekt.get<Gson>().fromJson(MOCK_USAGE_ANALYTICS, AnalyticsUsageHolder::class.java).analyticsUsage
        }
    }
}

private const val MOCK_USAGE_ANALYTICS =
    "{\n" +
            "    \"table\": {\n" +
            "        \"Date\": [\n" +
            "            \"2022-08-10 12:39:16\",\n" +
            "            \"2022-10-03 14:26:55\",\n" +
            "            \"2022-09-20 16:54:58\",\n" +
            "            \"2022-08-11 12:02:09\",\n" +
            "            \"2022-11-08 12:13:07\",\n" +
            "            \"2022-10-03 12:02:55\",\n" +
            "            \"2022-09-26 10:33:59\",\n" +
            "            \"2022-10-03 11:02:47\",\n" +
            "            \"2022-10-03 10:34:17\",\n" +
            "            \"2022-09-03 12:57:47\",\n" +
            "            \"2022-09-26 10:45:20\",\n" +
            "            \"2022-09-03 12:54:38\",\n" +
            "            \"2022-10-03 15:19:37\",\n" +
            "            \"2022-08-10 11:53:26\",\n" +
            "            \"2022-09-26 10:24:06\",\n" +
            "            \"2022-11-08 12:39:36\",\n" +
            "            \"2022-10-03 11:51:40\",\n" +
            "            \"2022-09-26 10:31:44\",\n" +
            "            \"2022-10-03 10:43:37\",\n" +
            "            \"2022-10-03 11:06:06\",\n" +
            "            \"2022-09-26 10:42:11\",\n" +
            "            \"2022-09-26 10:47:34\",\n" +
            "            \"2022-10-03 13:42:40\",\n" +
            "            \"2022-10-03 15:39:04\",\n" +
            "            \"2022-08-05 14:04:48\",\n" +
            "            \"2022-09-03 13:02:43\",\n" +
            "            \"2022-08-05 14:41:12\",\n" +
            "            \"2022-10-03 13:47:40\",\n" +
            "            \"2022-09-26 10:20:36\",\n" +
            "            \"2022-09-26 10:39:40\",\n" +
            "            \"2022-09-21 14:10:03\",\n" +
            "            \"2022-10-03 11:54:35\",\n" +
            "            \"2022-08-11 12:05:13\",\n" +
            "            \"2022-09-26 10:14:46\",\n" +
            "            \"2022-09-10 19:16:27\",\n" +
            "            \"2022-09-07 12:29:45\",\n" +
            "            \"2022-09-03 12:52:48\",\n" +
            "            \"2022-08-09 14:31:36\"\n" +
            "        ],\n" +
            "        \"Paint Used (Ml)\": [\n" +
            "            163.127,\n" +
            "            175.36,\n" +
            "            156.011,\n" +
            "            204.29,\n" +
            "            59.138,\n" +
            "            6.306,\n" +
            "            396.057,\n" +
            "            140.26,\n" +
            "            178.071,\n" +
            "            314.601,\n" +
            "            126.844,\n" +
            "            134.495,\n" +
            "            317.657,\n" +
            "            576.694,\n" +
            "            176.533,\n" +
            "            119.165,\n" +
            "            6.31,\n" +
            "            191.535,\n" +
            "            124.147,\n" +
            "            162.613,\n" +
            "            103.452,\n" +
            "            394.568,\n" +
            "            132.248,\n" +
            "            237.101,\n" +
            "            50.392,\n" +
            "            285.719,\n" +
            "            65.97,\n" +
            "            144.418,\n" +
            "            276.086,\n" +
            "            191.219,\n" +
            "            93.024,\n" +
            "            3.995,\n" +
            "            308.932,\n" +
            "            487.365,\n" +
            "            254.063,\n" +
            "            167.858,\n" +
            "            115.167,\n" +
            "            318.179\n" +
            "        ],\n" +
            "        \"Part\": [\n" +
            "            \"Fender\",\n" +
            "            \"Fender\",\n" +
            "            \"Hood\",\n" +
            "            \"Fender\",\n" +
            "            \"Fender\",\n" +
            "            \"Fender\",\n" +
            "            \"Wing\",\n" +
            "            \"Fender\",\n" +
            "            \"Fender\",\n" +
            "            \"Fender\",\n" +
            "            \"Roof Tile\",\n" +
            "            \"Fender\",\n" +
            "            \"Door\",\n" +
            "            \"Door\",\n" +
            "            \"Fender\",\n" +
            "            \"Roof Tile\",\n" +
            "            \"Fender\",\n" +
            "            \"Tail Wing\",\n" +
            "            \"Fender\",\n" +
            "            \"Fender\",\n" +
            "            \"Beam\",\n" +
            "            \"Rail Section\",\n" +
            "            \"Fender\",\n" +
            "            \"Door\",\n" +
            "            \"Fender\",\n" +
            "            \"Fender\",\n" +
            "            \"Hood\",\n" +
            "            \"Fender\",\n" +
            "            \"Hood\",\n" +
            "            \"Propeller\",\n" +
            "            \"Fender\",\n" +
            "            \"Fender\",\n" +
            "            \"Fender\",\n" +
            "            \"Door\",\n" +
            "            \"Fender\",\n" +
            "            \"Fender\",\n" +
            "            \"Fender\",\n" +
            "            \"Roof Tile\"\n" +
            "        ],\n" +
            "        \"Session ID\": [\n" +
            "            \"0OeU52LdgCv\",\n" +
            "            \"25Lpkx6hNUI\",\n" +
            "            \"43CMPFgkbt2\",\n" +
            "            \"4qYf03dFlOT\",\n" +
            "            \"4ums7PTFGa3\",\n" +
            "            \"6GvcN05MjhO\",\n" +
            "            \"7meW6R5DnrH\",\n" +
            "            \"7oS2rE0vYeA\",\n" +
            "            \"7UH5xk3tIbS\",\n" +
            "            \"Ab0gu61dNMX\",\n" +
            "            \"AHg0j4OkfF5\",\n" +
            "            \"aLPh7n0KeW5\",\n" +
            "            \"AnvRJ1thB78\",\n" +
            "            \"AxWH047Nnup\",\n" +
            "            \"BO0qak23SCx\",\n" +
            "            \"cEtXH50eMo3\",\n" +
            "            \"E7TyaM8nuP6\",\n" +
            "            \"fW0s2twN1DM\",\n" +
            "            \"gT3GWAi0n4p\",\n" +
            "            \"IHw7Xr86dYk\",\n" +
            "            \"Ij34FCl5wyY\",\n" +
            "            \"jNJ0n8aKtI7\",\n" +
            "            \"Kqsm7XOH3h4\",\n" +
            "            \"Lu17GHJl4gr\",\n" +
            "            \"LvPgWp8xM54\",\n" +
            "            \"MkGUid726Fw\",\n" +
            "            \"n87wV6QAuaH\",\n" +
            "            \"NYsn0Pf61Mg\",\n" +
            "            \"oc5gT03fRCE\",\n" +
            "            \"PtonQ6T42Gk\",\n" +
            "            \"taRdT8Qw14V\",\n" +
            "            \"tEK68JfV3ir\",\n" +
            "            \"Ua5mH0IxBw4\",\n" +
            "            \"v78GJa2rDyR\",\n" +
            "            \"vmK8SkM4Oj3\",\n" +
            "            \"Wj07Aa4wIxF\",\n" +
            "            \"WUBogY5p71w\",\n" +
            "            \"YSa1H3pkgA8\"\n" +
            "        ],\n" +
            "        \"Total Time Spent (min)\": [\n" +
            "            2.067,\n" +
            "            1.683,\n" +
            "            2.133,\n" +
            "            1.983,\n" +
            "            0.967,\n" +
            "            1.933,\n" +
            "            3.217,\n" +
            "            1.433,\n" +
            "            1.767,\n" +
            "            2.883,\n" +
            "            1.233,\n" +
            "            1.733,\n" +
            "            2.567,\n" +
            "            5.067,\n" +
            "            1.683,\n" +
            "            2.117,\n" +
            "            1.8,\n" +
            "            1.85,\n" +
            "            17.817,\n" +
            "            1.567,\n" +
            "            1.133,\n" +
            "            2.983,\n" +
            "            1.85,\n" +
            "            1.917,\n" +
            "            0.7,\n" +
            "            2.383,\n" +
            "            0.75,\n" +
            "            2.25,\n" +
            "            2.817,\n" +
            "            1.933,\n" +
            "            0.9,\n" +
            "            1.633,\n" +
            "            6.3,\n" +
            "            4.8,\n" +
            "            5.15,\n" +
            "            1.533,\n" +
            "            1.517,\n" +
            "            2.833\n" +
            "        ],\n" +
            "        \"User\": [\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\",\n" +
            "            \"Koche Veljanovski\"\n" +
            "        ]\n" +
            "    }\n" +
            "}"
