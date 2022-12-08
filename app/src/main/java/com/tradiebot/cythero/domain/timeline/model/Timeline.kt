package com.tradiebot.cythero.domain.timeline.model

import com.google.gson.Gson
import java.io.Serializable

@Deprecated("")
data class Timeline (
    val timeline: List<TimelineEntry>,
): Serializable {
    companion object {
        fun mockInstance() = Gson().fromJson(MOCK_TIMELINE, Timeline::class.java)!!
    }
}

@Deprecated("")
data class TimelineEntry (
    val application_id: Long,
    val session_end: String,
    val session_start: String,
    val time_spent: Float,
    val user_id: Long,
): Serializable

private const val MOCK_TIMELINE =
            "{\n" +
            "  \"timeline\": [\n" +
            "    {\n" +
            "      \"application_id\": 1,\n" +
            "      \"session_end\": \"2022-08-05 14:05:30\",\n" +
            "      \"session_id\": \"LvPgWp8xM54\",\n" +
            "      \"session_start\": \"2022-08-05 14:04:48\",\n" +
            "      \"time_spent\": 0.7,\n" +
            "      \"user_id\": 4\n" +
            "    },\n" +
            "    {\n" +
            "      \"application_id\": 1,\n" +
            "      \"session_end\": \"2022-08-05 14:41:57\",\n" +
            "      \"session_id\": \"n87wV6QAuaH\",\n" +
            "      \"session_start\": \"2022-08-05 14:41:12\",\n" +
            "      \"time_spent\": 0.75,\n" +
            "      \"user_id\": 4\n" +
            "    },\n" +
            "    {\n" +
            "      \"application_id\": 1,\n" +
            "      \"session_end\": \"2022-08-09 14:34:26\",\n" +
            "      \"session_id\": \"YSa1H3pkgA8\",\n" +
            "      \"session_start\": \"2022-08-09 14:31:36\",\n" +
            "      \"time_spent\": 2.833,\n" +
            "      \"user_id\": 4\n" +
            "    },\n" +
            "    {\n" +
            "      \"application_id\": 1,\n" +
            "      \"session_end\": \"2022-08-10 11:58:30\",\n" +
            "      \"session_id\": \"AxWH047Nnup\",\n" +
            "      \"session_start\": \"2022-08-10 11:53:26\",\n" +
            "      \"time_spent\": 5.067,\n" +
            "      \"user_id\": 4\n" +
            "    },\n" +
            "    {\n" +
            "      \"application_id\": 1,\n" +
            "      \"session_end\": \"2022-08-10 12:41:20\",\n" +
            "      \"session_id\": \"0OeU52LdgCv\",\n" +
            "      \"session_start\": \"2022-08-10 12:39:16\",\n" +
            "      \"time_spent\": 2.067,\n" +
            "      \"user_id\": 4\n" +
            "    },\n" +
            "    {\n" +
            "      \"application_id\": 1,\n" +
            "      \"session_end\": \"2022-08-11 12:04:08\",\n" +
            "      \"session_id\": \"4qYf03dFlOT\",\n" +
            "      \"session_start\": \"2022-08-11 12:02:09\",\n" +
            "      \"time_spent\": 1.983,\n" +
            "      \"user_id\": 4\n" +
            "    },\n" +
            "    {\n" +
            "      \"application_id\": 1,\n" +
            "      \"session_end\": \"2022-08-11 12:11:31\",\n" +
            "      \"session_id\": \"Ua5mH0IxBw4\",\n" +
            "      \"session_start\": \"2022-08-11 12:05:13\",\n" +
            "      \"time_spent\": 6.3,\n" +
            "      \"user_id\": 4\n" +
            "    },\n" +
            "    {\n" +
            "      \"application_id\": 1,\n" +
            "      \"session_end\": \"2022-09-03 12:54:19\",\n" +
            "      \"session_id\": \"WUBogY5p71w\",\n" +
            "      \"session_start\": \"2022-09-03 12:52:48\",\n" +
            "      \"time_spent\": 1.517,\n" +
            "      \"user_id\": 4\n" +
            "    },\n" +
            "    {\n" +
            "      \"application_id\": 1,\n" +
            "      \"session_end\": \"2022-09-03 12:56:22\",\n" +
            "      \"session_id\": \"aLPh7n0KeW5\",\n" +
            "      \"session_start\": \"2022-09-03 12:54:38\",\n" +
            "      \"time_spent\": 1.733,\n" +
            "      \"user_id\": 4\n" +
            "    },\n" +
            "    {\n" +
            "      \"application_id\": 1,\n" +
            "      \"session_end\": \"2022-09-03 13:00:40\",\n" +
            "      \"session_id\": \"Ab0gu61dNMX\",\n" +
            "      \"session_start\": \"2022-09-03 12:57:47\",\n" +
            "      \"time_spent\": 2.883,\n" +
            "      \"user_id\": 4\n" +
            "    },\n" +
            "    {\n" +
            "      \"application_id\": 1,\n" +
            "      \"session_end\": \"2022-09-03 13:05:06\",\n" +
            "      \"session_id\": \"MkGUid726Fw\",\n" +
            "      \"session_start\": \"2022-09-03 13:02:43\",\n" +
            "      \"time_spent\": 2.383,\n" +
            "      \"user_id\": 4\n" +
            "    }\n" +
            "  ]\n" +
            "}"