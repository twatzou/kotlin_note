package com.example.note.security

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotBlank
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
open class User(
        @Id
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        @Column(columnDefinition = "varchar(36)")
        var id: String = "",

        @Column(unique = true, nullable = false)
        @NotNull
        @Email
        var email: String = "",

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @NotBlank
        var pwd: String = "",

        @NotBlank
        var firstName: String = "",

        @NotBlank
        var lastName: String = "",
        var roles: String = "",
        var enabled: Boolean = true,
        var accountNonExpired: Boolean = true, var accountNonLocked: Boolean = true,
        var credentialsNonExpired: Boolean = true,
        @CreationTimestamp
        var created: Date = Date(),
        @UpdateTimestamp
        var modified: Date = Date()
        )
 : UserDetails {

    constructor() : this(
            "", "", "", "", "", "",
            true, true, true, true,
            Date(), Date()
    )

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = mutableListOf<GrantedAuthority>()
        roles
                .split(",")
                .forEach{it ->
                    authorities.add(
                            SimpleGrantedAuthority(
                                    it.trim()
                            )
                    )
                }
        return authorities
    }

    override fun isEnabled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getUsername(): String {
        TODO("Not yet implemented")
    }

    override fun isCredentialsNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPassword(): String {
        TODO("Not yet implemented")
    }

    override fun isAccountNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isAccountNonLocked(): Boolean {
        TODO("Not yet implemented")
    }
}