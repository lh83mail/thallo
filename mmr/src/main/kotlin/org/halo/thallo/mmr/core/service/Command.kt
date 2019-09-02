package org.halo.thallo.mmr.core.service

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface Command {
    fun execute(requestData: Map<String, Any>,
                request: HttpServletRequest,
                response: HttpServletResponse): Any
}