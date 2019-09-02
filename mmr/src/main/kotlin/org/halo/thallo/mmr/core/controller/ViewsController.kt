package org.halo.thallo.mmr.core.controller

import org.halo.thallo.mmr.core.service.ViewManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 视图命令服务
 */
@RestController
@RequestMapping("/v1/views")
class ViewsController(@Autowired val viewManager: ViewManager) {


    /**
     * 执行数据命令
     * @param vid 视图ID
     * @param cid 命令ID
     * @param requestData
     */
    @RequestMapping("/{vid}/commands/{cid}")
    fun execute(
            @PathVariable vid: String,
            @PathVariable cid: String,
            @RequestBody requestData: Map<String, Any>,
            request: HttpServletRequest,
            response: HttpServletResponse ): Any {
        val view = viewManager.load(vid)
        val command = view.createCommand(cid)
        return command.execute(requestData, request, response)
    }

}