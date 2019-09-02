package org.halo.thallo.mmr.core.service

import org.halo.thallo.mmr.core.model.Attribute

interface View {
    val attributes: List<Attribute>

    fun createCommand(cid: String): Command
}