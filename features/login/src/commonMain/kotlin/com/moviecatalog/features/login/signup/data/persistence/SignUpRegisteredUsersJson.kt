package com.moviecatalog.features.login.signup.data.persistence

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
private data class PersistedUserAccountsDto(
    val entries: List<UserPasswordEntryDto> = emptyList(),
)

@Serializable
private data class UserPasswordEntryDto(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
)

internal object SignUpRegisteredUsersJson {
    private val json =
        Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
        }

    fun encode(map: Map<String, String>): ByteArray =
        json
            .encodeToString(
                PersistedUserAccountsDto(
                    entries =
                        map.map { (username, password) ->
                            UserPasswordEntryDto(
                                username = username,
                                password = password,
                            )
                        },
                ),
            )
            .encodeToByteArray()

    fun decode(bytes: ByteArray): Map<String, String> {
        if (bytes.isEmpty()) return emptyMap()
        val dto = json.decodeFromString<PersistedUserAccountsDto>(bytes.decodeToString())
        return dto.entries.associate { it.username to it.password }
    }
}
