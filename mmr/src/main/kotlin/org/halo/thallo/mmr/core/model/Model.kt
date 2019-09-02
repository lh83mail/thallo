package org.halo.thallo.mmr.core.model

interface Model {
    var id: String
    var name: String
    var description: String?

    fun clone(): Model
}