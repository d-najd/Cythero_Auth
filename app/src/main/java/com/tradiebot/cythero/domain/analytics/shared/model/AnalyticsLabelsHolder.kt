package com.tradiebot.cythero.domain.analytics.shared.model


import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.io.Serializable

data class AnalyticsLabelsHolder(
    @SerializedName("labels") val analyticsLabels: List<AnalyticsLabel>
): Serializable

data class AnalyticsLabel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
): Serializable {
    companion object {
        fun mockInstance(): List<AnalyticsLabel> {
            // This may crash if we don't do this
            val temp = Injekt.get<Gson>().fromJson(MOCK_ANALYTICS_LABELS, AnalyticsLabelsHolder::class.java)
            
            return temp.analyticsLabels
        }
    }
}

private const val MOCK_ANALYTICS_LABELS = "" +
    "{\n" +
    "  \"labels\": [\n" +
    "    null,\n" +
    "    {\n" +
    "      \"id\": 1,\n" +
    "      \"name\": \"Cythero Launcher Launch\"\n" +
    "    },\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    null,\n" +
    "    {\n" +
    "      \"id\": 88,\n" +
    "      \"name\": \"file\"\n" +
    "    },\n" +
    "    null,\n" +
    "    {\n" +
    "      \"id\": 90,\n" +
    "      \"name\": \"Primer Mils\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 91,\n" +
    "      \"name\": \"Color Mils\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 92,\n" +
    "      \"name\": \"Clear Mils\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 93,\n" +
    "      \"name\": \"Part Type\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 94,\n" +
    "      \"name\": \"Paint Time Start\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 95,\n" +
    "      \"name\": \"Paint Time Stop\"\n" +
    "    },\n" +
    "    null,\n" +
    "    null,\n" +
    "    {\n" +
    "      \"id\": 98,\n" +
    "      \"name\": \"Overall Time\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 99,\n" +
    "      \"name\": \"Overall Paint used\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 100,\n" +
    "      \"name\": \"Overall Grade\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 101,\n" +
    "      \"name\": \"Overall Good Coverage\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 102,\n" +
    "      \"name\": \"Overall Low Coverage\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 103,\n" +
    "      \"name\": \"Overall High coverage\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 104,\n" +
    "      \"name\": \"Primer Time\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 105,\n" +
    "      \"name\": \"Primer Paint used\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 106,\n" +
    "      \"name\": \"Primer Grade\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 107,\n" +
    "      \"name\": \"Primer Good Coverage\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 108,\n" +
    "      \"name\": \"Primer Low Coverage\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 109,\n" +
    "      \"name\": \"Primer High coverage\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 110,\n" +
    "      \"name\": \"Base Time\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 111,\n" +
    "      \"name\": \"Base Paint used\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 112,\n" +
    "      \"name\": \"Base Grade\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 113,\n" +
    "      \"name\": \"Base Good Coverage\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 114,\n" +
    "      \"name\": \"Base Low Coverage\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 115,\n" +
    "      \"name\": \"Base High coverage\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 116,\n" +
    "      \"name\": \"Clear Time\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 117,\n" +
    "      \"name\": \"Clear Paint used\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 118,\n" +
    "      \"name\": \"Clear Grade\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 119,\n" +
    "      \"name\": \"Clear Good Coverage\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 120,\n" +
    "      \"name\": \"Clear Low Coverage\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 121,\n" +
    "      \"name\": \"Clear High coverage\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 122,\n" +
    "      \"name\": \"Session Start\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 123,\n" +
    "      \"name\": \"Session End\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"id\": 124,\n" +
    "      \"name\": \"Part Name\"\n" +
    "    }\n" +
    "  ]\n" +
    "}"