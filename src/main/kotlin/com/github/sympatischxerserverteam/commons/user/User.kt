package com.github.sympatischxerserverteam.commons.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity(name = "user")
open class User() {
    @Id
    lateinit var id: UUID
        protected set

    @Column(name = "name", length = 16, unique = false, nullable = false)
    lateinit var name: String
        protected set

    constructor(uuid: UUID, name: String) : this() {
        this.id = uuid
        this.name = name
    }
}