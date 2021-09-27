package me.saro.jwt.kotlin.model

import io.jsonwebtoken.SignatureAlgorithm
import me.saro.jwt.model.KeyChain
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.security.GeneralSecurityException

@DisplayName("[Kotlin] KeyChain")
class KeyChainTest{

    @Test
    fun `crypt success`() {
        val signatureAlgorithm = SignatureAlgorithm.RS256
        val kc = KeyChain.create(signatureAlgorithm)

        val text = "안녕하세요. 간단한 암/복호화 test 입니다."
        val encrypt = kc.encrypt(text)
        val decrypt = kc.decrypt(encrypt)

        println(kc)

        println("--------")
        println(text)
        println(encrypt)

        assert(text != encrypt)
        assert(text == decrypt)
    }

    @Test
    fun `crypt exception`() {
        val signatureAlgorithm = SignatureAlgorithm.RS256
        val kc1 = KeyChain.create(signatureAlgorithm)
        val kc2 = KeyChain.create(signatureAlgorithm)

        println(kc1)
        println(kc2)

        val text = "안녕하세요. 간단한 암/복호화 test 입니다."
        val encrypt = kc1.encrypt(text)

        assertThrows<GeneralSecurityException>{ kc2.decrypt(encrypt) }
    }


    @Test
    fun `serialize and deserialize`() {
        val signatureAlgorithm = SignatureAlgorithm.RS256
        val kc1 = KeyChain.create(signatureAlgorithm)
        val kc2 = KeyChain.deserialize(kc1.serialize())

        println(kc1)
        println(kc2)

        val text = "안녕하세요. 간단한 암/복호화 test 입니다."
        val encrypt = kc1.encrypt(text)
        val decrypt = kc2.decrypt(encrypt)

        println("--------")
        println(text)
        println(encrypt)

        assert(text != encrypt)
        assert(text == decrypt)
    }
}

