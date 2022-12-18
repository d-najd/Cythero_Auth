package com.tradiebot.cythero.domain.analytics.shared.model


import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

data class AnalyticsSessionHolder(
    @SerializedName("analytics") val analyticSessions: List<AnalyticSession>
)

data class AnalyticSession(
    @SerializedName("application_id") val applicationId: Int,
    @SerializedName("date") val date: String,
    @SerializedName("id") val id: Int,
    @SerializedName("label_id") val labelId: Int,
    @SerializedName("score") val score: String,
    @SerializedName("session_id") val sessionId: String,
    @SerializedName("user_id") val userId: Int
) {
    companion object {
        fun mockInstance(): List<AnalyticSession> {
            // This may crash if we don't do this
            val temp = Injekt.get<Gson>().fromJson(MOCK_ANALYTIC_SESSION, AnalyticsSessionHolder::class.java)
        
            return temp.analyticSessions
        }
    }
}

private const val MOCK_ANALYTIC_SESSION = "" +
    "{\n" +
    "  \"analytics\": [\n" +
    "    {\n" +
    "      \"application_id\": 1,\n" +
    "      \"date\": \"Tue, 08 Nov 2022 11:14:06 GMT\",\n" +
    "      \"id\": 1632,\n" +
    "      \"label_id\": 104,\n" +
    "      \"score\": \"21\",\n" +
    "      \"session_id\": \"4ums7PTFGa3\",\n" +
    "      \"user_id\": 4\n" +
    "    },\n" +
    "    {\n" +
    "      \"application_id\": 1,\n" +
    "      \"date\": \"Tue, 08 Nov 2022 11:14:06 GMT\",\n" +
    "      \"id\": 1633,\n" +
    "      \"label_id\": 105,\n" +
    "      \"score\": \"19.0883\",\n" +
    "      \"session_id\": \"4ums7PTFGa3\",\n" +
    "      \"user_id\": 4\n" +
    "    },\n" +
    "    {\n" +
    "      \"application_id\": 1,\n" +
    "      \"date\": \"Tue, 08 Nov 2022 11:14:06 GMT\",\n" +
    "      \"id\": 1634,\n" +
    "      \"label_id\": 106,\n" +
    "      \"score\": \"B\",\n" +
    "      \"session_id\": \"4ums7PTFGa3\",\n" +
    "      \"user_id\": 4\n" +
    "    },\n" +
    "    {\n" +
    "      \"application_id\": 1,\n" +
    "      \"date\": \"Tue, 08 Nov 2022 11:14:06 GMT\",\n" +
    "      \"id\": 1635,\n" +
    "      \"label_id\": 107,\n" +
    "      \"score\": \"47.99063\",\n" +
    "      \"session_id\": \"4ums7PTFGa3\",\n" +
    "      \"user_id\": 4\n" +
    "    },\n" +
    "    {\n" +
    "      \"application_id\": 1,\n" +
    "      \"date\": \"Tue, 08 Nov 2022 11:14:06 GMT\",\n" +
    "      \"id\": 1636,\n" +
    "      \"label_id\": 108,\n" +
    "      \"score\": \"52.00995\",\n" +
    "      \"session_id\": \"4ums7PTFGa3\",\n" +
    "      \"user_id\": 4\n" +
    "    },\n" +
    "    {\n" +
    "      \"application_id\": 1,\n" +
    "      \"date\": \"Tue, 08 Nov 2022 11:14:06 GMT\",\n" +
    "      \"id\": 1637,\n" +
    "      \"label_id\": 109,\n" +
    "      \"score\": \"0\",\n" +
    "      \"session_id\": \"4ums7PTFGa3\",\n" +
    "      \"user_id\": 4\n" +
    "    },\n" +
    "    {\n" +
    "      \"application_id\": 1,\n" +
    "      \"date\": \"Tue, 08 Nov 2022 11:14:06 GMT\",\n" +
    "      \"id\": 1638,\n" +
    "      \"label_id\": 110,\n" +
    "      \"score\": \"17\",\n" +
    "      \"session_id\": \"4ums7PTFGa3\",\n" +
    "      \"user_id\": 4\n" +
    "    },\n" +
    "    {\n" +
    "      \"application_id\": 1,\n" +
    "      \"date\": \"Tue, 08 Nov 2022 11:14:06 GMT\",\n" +
    "      \"id\": 1639,\n" +
    "      \"label_id\": 111,\n" +
    "      \"score\": \"20.52419\",\n" +
    "      \"session_id\": \"4ums7PTFGa3\",\n" +
    "      \"user_id\": 4\n" +
    "    },\n" +
    "    {\n" +
    "      \"application_id\": 1,\n" +
    "      \"date\": \"Tue, 08 Nov 2022 11:14:06 GMT\",\n" +
    "      \"id\": 1640,\n" +
    "      \"label_id\": 112,\n" +
    "      \"score\": \"A\",\n" +
    "      \"session_id\": \"4ums7PTFGa3\",\n" +
    "      \"user_id\": 4\n" +
    "    },\n" +
    "    {\n" +
    "      \"application_id\": 1,\n" +
    "      \"date\": \"Tue, 08 Nov 2022 11:14:06 GMT\",\n" +
    "      \"id\": 1641,\n" +
    "      \"label_id\": 113,\n" +
    "      \"score\": \"62.33812\",\n" +
    "      \"session_id\": \"4ums7PTFGa3\",\n" +
    "      \"user_id\": 4\n" +
    "    },\n" +
    "    {\n" +
    "      \"application_id\": 1,\n" +
    "      \"date\": \"Tue, 08 Nov 2022 11:14:06 GMT\",\n" +
    "      \"id\": 1642,\n" +
    "      \"label_id\": 114,\n" +
    "      \"score\": \"30.85744\",\n" +
    "      \"session_id\": \"4ums7PTFGa3\",\n" +
    "      \"user_id\": 4\n" +
    "    },\n" +
    "    {\n" +
    "      \"application_id\": 1,\n" +
    "      \"date\": \"Tue, 08 Nov 2022 11:14:06 GMT\",\n" +
    "      \"id\": 1643,\n" +
    "      \"label_id\": 115,\n" +
    "      \"score\": \"6.805035\",\n" +
    "      \"session_id\": \"4ums7PTFGa3\",\n" +
    "      \"user_id\": 4\n" +
    "    },\n" +
    "    {\n" +
    "      \"application_id\": 1,\n" +
    "      \"date\": \"Tue, 08 Nov 2022 11:14:06 GMT\",\n" +
    "      \"id\": 1644,\n" +
    "      \"label_id\": 116,\n" +
    "      \"score\": \"14\",\n" +
    "      \"session_id\": \"4ums7PTFGa3\",\n" +
    "      \"user_id\": 4\n" +
    "    },\n" +
    "    {\n" +
    "      \"application_id\": 1,\n" +
    "      \"date\": \"Tue, 08 Nov 2022 11:14:06 GMT\",\n" +
    "      \"id\": 1645,\n" +
    "      \"label_id\": 117,\n" +
    "      \"score\": \"19.52573\",\n" +
    "      \"session_id\": \"4ums7PTFGa3\",\n" +
    "      \"user_id\": 4\n" +
    "    },\n" +
    "    {\n" +
    "      \"application_id\": 1,\n" +
    "      \"date\": \"Tue, 08 Nov 2022 11:14:06 GMT\",\n" +
    "      \"id\": 1646,\n" +
    "      \"label_id\": 118,\n" +
    "      \"score\": \"A\",\n" +
    "      \"session_id\": \"4ums7PTFGa3\",\n" +
    "      \"user_id\": 4\n" +
    "    },\n" +
    "    {\n" +
    "      \"application_id\": 1,\n" +
    "      \"date\": \"Tue, 08 Nov 2022 11:14:06 GMT\",\n" +
    "      \"id\": 1647,\n" +
    "      \"label_id\": 119,\n" +
    "      \"score\": \"69.31967\",\n" +
    "      \"session_id\": \"4ums7PTFGa3\",\n" +
    "      \"user_id\": 4\n" +
    "    },\n" +
    "    {\n" +
    "      \"application_id\": 1,\n" +
    "      \"date\": \"Tue, 08 Nov 2022 11:14:06 GMT\",\n" +
    "      \"id\": 1648,\n" +
    "      \"label_id\": 120,\n" +
    "      \"score\": \"28.44204\",\n" +
    "      \"session_id\": \"4ums7PTFGa3\",\n" +
    "      \"user_id\": 4\n" +
    "    },\n" +
    "    {\n" +
    "      \"application_id\": 1,\n" +
    "      \"date\": \"Tue, 08 Nov 2022 11:14:06 GMT\",\n" +
    "      \"id\": 1649,\n" +
    "      \"label_id\": 121,\n" +
    "      \"score\": \"2.238876\",\n" +
    "      \"session_id\": \"4ums7PTFGa3\",\n" +
    "      \"user_id\": 4\n" +
    "    },\n" +
    "    {\n" +
    "      \"application_id\": 1,\n" +
    "      \"date\": \"Tue, 08 Nov 2022 11:14:06 GMT\",\n" +
    "      \"id\": 1650,\n" +
    "      \"label_id\": 122,\n" +
    "      \"score\": \"2022-11-08 12:13:07\",\n" +
    "      \"session_id\": \"4ums7PTFGa3\",\n" +
    "      \"user_id\": 4\n" +
    "    },\n" +
    "    {\n" +
    "      \"application_id\": 1,\n" +
    "      \"date\": \"Tue, 08 Nov 2022 11:14:06 GMT\",\n" +
    "      \"id\": 1651,\n" +
    "      \"label_id\": 123,\n" +
    "      \"score\": \"2022-11-08 12:14:05\",\n" +
    "      \"session_id\": \"4ums7PTFGa3\",\n" +
    "      \"user_id\": 4\n" +
    "    },\n" +
    "    {\n" +
    "      \"application_id\": 1,\n" +
    "      \"date\": \"Tue, 08 Nov 2022 11:14:06 GMT\",\n" +
    "      \"id\": 1652,\n" +
    "      \"label_id\": 124,\n" +
    "      \"score\": \"Fender\",\n" +
    "      \"session_id\": \"4ums7PTFGa3\",\n" +
    "      \"user_id\": 4\n" +
    "    }\n" +
    "  ]\n" +
    "}"
