package com.tradiebot.cythero.domain.auth.model

import com.google.gson.Gson
import com.tradiebot.cythero.domain.user.model.User
import java.io.Serializable

data class Auth(
    val organization: String?,
    val refresh: String,
    val token: String,
    val user: User,
) : Serializable {
    companion object{
        fun mockInstance(): Auth {
            return Gson().fromJson(MOCK_AUTH, Auth::class.java)
        }
    }
}

private const val MOCK_AUTH =
            "{\n" +
            "    \"organization\": \"Cythero VR\",\n" +
            "    \"refresh\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY3MDI2MDk5OSwianRpIjoiN2IxMDNlYjMtMjlkNy00Mzg1LWFkNGQtZGJmNTZmYWRmMmNkIiwidHlwZSI6InJlZnJlc2giLCJzdWIiOnsib3JnYW5pemF0aW9uX2lkIjoxLCJpZCI6NzAsImxhc3ROYW1lIjoiTmFqZG92c2tpIiwidXNlcm5hbWUiOiJEaW1pdGFyTmFqZG92c2tpMSIsInNpZ25fdXBfZGF0ZSI6IlNhdCwgMjYgTm92IDIwMjIgMjA6MDM6MjUgR01UIiwidHlwZV9pZCI6MywiZmlyc3ROYW1lIjoiRGltaXRhciIsImVtYWlsIjoiZGltaXRhci5uYWpkb3Zza2kuZXhhbXBsZTFAZ21haWwuY29tIiwiYXJjaGl2ZWQiOjB9LCJuYmYiOjE2NzAyNjA5OTksImV4cCI6MTY3Mjg1Mjk5OX0.C3eC7LOnZMRTeEKF1kz1lD3j5elnZvS3_M_gD7fLH50\",\n" +
            "    \"token\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY3MDI2MDk5OSwianRpIjoiYTZkYzU2OGUtZGFiNi00NTJmLTkxZjMtOGMzYThlZTk3MzYyIiwidHlwZSI6ImFjY2VzcyIsInN1YiI6eyJvcmdhbml6YXRpb25faWQiOjEsImlkIjo3MCwibGFzdE5hbWUiOiJOYWpkb3Zza2kiLCJ1c2VybmFtZSI6IkRpbWl0YXJOYWpkb3Zza2kxIiwic2lnbl91cF9kYXRlIjoiU2F0LCAyNiBOb3YgMjAyMiAyMDowMzoyNSBHTVQiLCJ0eXBlX2lkIjozLCJmaXJzdE5hbWUiOiJEaW1pdGFyIiwiZW1haWwiOiJkaW1pdGFyLm5hamRvdnNraS5leGFtcGxlMUBnbWFpbC5jb20iLCJhcmNoaXZlZCI6MH0sIm5iZiI6MTY3MDI2MDk5OSwiZXhwIjoxNjcxNTU2OTk5fQ.JUodJhQMQXx1_bLjtL8Q0XNjPee_Ih2Tse6S4DAf1lE\",\n" +
            "    \"user\": {\n" +
            "        \"archived\": 0,\n" +
            "        \"email\": \"dimitar.najdovski.example1@gmail.com\",\n" +
            "        \"firstName\": \"Dimitar\",\n" +
            "        \"id\": 70,\n" +
            "        \"lastName\": \"Najdovski\",\n" +
            "        \"organization_id\": 1,\n" +
            "        \"sign_up_date\": \"Sat, 26 Nov 2022 20:03:25 GMT\",\n" +
            "        \"type_id\": 3,\n" +
            "        \"username\": \"DimitarNajdovski1\"\n" +
            "    }\n" +
            "}"