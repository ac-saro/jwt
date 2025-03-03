package me.saro.jwt.tbd

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.util.*

@Deprecated("this class is TBD")
class JwtConverter {
    companion object {
        private val base64Decoder = Base64.getDecoder()
        private val objectMapper = jacksonObjectMapper()

        @JvmStatic
        fun toMap(base64: String): Map<String, Any> {
            val text = try {
                String(base64Decoder.decode(base64))
            } catch (e: Exception) {
                throw JwtException("jwt base64 decoding failed: $base64")
            }

            return try {
                objectMapper.readValue(text, object: TypeReference<Map<String, Any>>(){})
            } catch (e: Exception) {
                throw JwtException("invalid jwt json: $text from $base64")
            }
        }
    }
}