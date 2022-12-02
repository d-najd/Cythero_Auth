package com.tradiebot.cythero.domain.auth.model

import com.google.gson.Gson
import com.tradiebot.cythero.domain.user.model.User
import java.io.Serializable

data class Auth(
    val organization: Long?,
    val refresh: String,
    val token: String,
    val user: User,
) : Serializable {
    companion object{
        fun testingInstance(): Auth {
            return Gson().fromJson(TESTING_AUTH, Auth::class.java)
        }
    }
}

private const val TESTING_AUTH =
        "{\n" +
        "    \"organization\": \"Cythero VR\",\n" +
        "    \"refresh\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY3MDAxODY1NSwianRpIjoiNGI2MWMzOGMtYTY4Zi00MGE0LWE2ZjMtNjhhMWJmY2Q5ZDA4IiwidHlwZSI6InJlZnJlc2giLCJzdWIiOnsib3JnYW5pemF0aW9uX2lkIjoxLCJpZCI6NzAsImxhc3ROYW1lIjoiTmFqZG92c2tpIiwidXNlcm5hbWUiOiJEaW1pdGFyTmFqZG92c2tpMSIsInNpZ25fdXBfZGF0ZSI6IlNhdCwgMjYgTm92IDIwMjIgMjA6MDM6MjUgR01UIiwidHlwZV9pZCI6MywiZmlyc3ROYW1lIjoiRGltaXRhciIsImVtYWlsIjoiZGltaXRhci5uYWpkb3Zza2kuZXhhbXBsZTFAZ21haWwuY29tIiwiYXJjaGl2ZWQiOjB9LCJuYmYiOjE2NzAwMTg2NTUsImV4cCI6MTY3MjYxMDY1NX0.XOsMiWzvlWlKlmmavOK95eQ3ub2um8Y9Kkr5pce76pI\",\n" +
        "    \"token\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY3MDAxODY1NSwianRpIjoiNWI5MGI3MjctN2ZkNi00MjcxLWJkMmQtNGVmOGMyMDY2NWU4IiwidHlwZSI6ImFjY2VzcyIsInN1YiI6eyJvcmdhbml6YXRpb25faWQiOjEsImlkIjo3MCwibGFzdE5hbWUiOiJOYWpkb3Zza2kiLCJ1c2VybmFtZSI6IkRpbWl0YXJOYWpkb3Zza2kxIiwic2lnbl91cF9kYXRlIjoiU2F0LCAyNiBOb3YgMjAyMiAyMDowMzoyNSBHTVQiLCJ0eXBlX2lkIjozLCJmaXJzdE5hbWUiOiJEaW1pdGFyIiwiZW1haWwiOiJkaW1pdGFyLm5hamRvdnNraS5leGFtcGxlMUBnbWFpbC5jb20iLCJhcmNoaXZlZCI6MH0sIm5iZiI6MTY3MDAxODY1NSwiZXhwIjoxNjcwMTA1MDU1fQ.bF4AlTptNIQ6dW39H9JRKscwcebg4jIJakIh4592OS4\",\n" +
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