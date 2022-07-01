package com.handoyo.ojekonlineapi.authentication


import com.fasterxml.jackson.databind.ObjectMapper
import com.handoyo.ojekonlineapi.Constant
import com.handoyo.ojekonlineapi.OjekonlineException
import com.handoyo.ojekonlineapi.user.services.UserServices
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.UnsupportedJwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationFilter : OncePerRequestFilter() {

    @Autowired
    private lateinit var userServices: UserServices

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            if (JwtConfig.isPermit(request)) {
                filterChain.doFilter(request, response)
            } else {
                val claims = validate(request)
                if (claims[Constant.CLAIMS] != null) {
                    setupAuthentication(claims) {
                        filterChain.doFilter(request, response)
                    }
                } else {
                    SecurityContextHolder.clearContext()
                    throw OjekonlineException("token required")
                }
            }
        } catch (e: Exception) {
            val errorResponse = com.handoyo.ojekonlineapi.BaseResponse<com.handoyo.ojekonlineapi.Empty>()
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.contentType = "application/json"

            when (e) {
                is UnsupportedJwtException -> {
                    errorResponse.message = "error unsupported!"
                    val responseString = ObjectMapper()
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(errorResponse)

                    response.writer.println(responseString)
                }
                else -> {
                    errorResponse.message = e.message ?: "token invalid!"
                    val responseString = ObjectMapper()
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(errorResponse)
                    response.writer.println(responseString)
                }
            }
        }

    }

    private fun validate(request: HttpServletRequest): Claims {
        val jwtToken = request.getHeader("Authorization")
        return Jwts.parserBuilder()
            .setSigningKey(Constant.SECRET.toByteArray())
            .build()
            .parseClaimsJws(jwtToken)
            .body
    }

    private fun setupAuthentication(claims: Claims, doOnNext: () -> Unit) {
        val authorities = claims[Constant.CLAIMS] as List<String>
        val authStream = authorities.stream().map { SimpleGrantedAuthority(it) }
            .collect(Collectors.toList())

        val auth = UsernamePasswordAuthenticationToken(claims.subject, null, authStream)
        SecurityContextHolder.getContext().authentication = auth
        val userId = SecurityContextHolder.getContext().authentication.principal as? String
        println("ASUUUUU subject ---> ${claims}")
        println("ASUUUUU user id ---> $userId")
        doOnNext.invoke()
    }
}