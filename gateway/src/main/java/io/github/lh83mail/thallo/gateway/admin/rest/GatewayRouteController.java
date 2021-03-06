package io.github.lh83mail.thallo.gateway.admin.rest;

import io.github.lh83mail.thallo.gateway.admin.service.GatewayRouteService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import io.github.lh83mail.thallo.common.web.response.ResponseItem;
import io.github.lh83mail.thallo.common.web.response.ResponseList;
import io.github.lh83mail.thallo.gateway.admin.model.GatewayRoute;
import io.github.lh83mail.thallo.gateway.admin.model.GatewayRouteQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static io.github.lh83mail.thallo.common.web.response.ResponseHelper.ok;
import static io.github.lh83mail.thallo.common.web.response.ResponseHelper.okList;

@RestController
@RequestMapping("/gateway/routes")
@Api("gateway routes")
@Slf4j
public class GatewayRouteController {

    @Autowired
    private GatewayRouteService gatewayRoutService;

    @ApiOperation(value = "新增网关路由", notes = "新增一个网关路由")
    @ApiImplicitParam(name = "gatewayRoutForm", value = "新增网关路由form表单", required = true, dataType = "GatewayRouteForm")
    @PostMapping
    public ResponseItem<GatewayRoute> add(@Valid @RequestBody GatewayRoute gatewayRout) {
        log.info("name:", gatewayRout);
        return ok(gatewayRoutService.add(gatewayRout));
    }

    @ApiOperation(value = "删除网关路由", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "网关路由ID", required = true, dataType = "long")
    @DeleteMapping(value = "/{id}")
    public ResponseItem<Boolean> delete(@PathVariable String id) {
        return ok(gatewayRoutService.delete(id));
    }

        @ApiOperation(value = "修改网关路由", notes = "修改指定网关路由信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "网关路由ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "gatewayRoutForm", value = "网关路由实体", required = true, dataType = "GatewayRouteForm")
    })
    @PutMapping(value = "/{id}")
    public ResponseItem<GatewayRoute> update(@PathVariable String id, @Valid @RequestBody GatewayRoute gatewayRout) {
        gatewayRout.setId(id);
        return ok(gatewayRoutService.update(gatewayRout));
    }

    @ApiOperation(value = "获取网关路由", notes = "根据id获取指定网关路由信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "网关路由ID", required = true, dataType = "long")
    @GetMapping(value = "/{id}")
    public ResponseItem<GatewayRoute> get(@PathVariable String id) {
        log.info("get with id:{}", id);
        return ok(gatewayRoutService.get(id));
    }

    @ApiOperation(value = "根据uri获取网关路由", notes = "根据uri获取网关路由信息，简单查询")
    @ApiImplicitParam(paramType = "query", name = "name", value = "网关路由路径", required = true, dataType = "string")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = ResponseItem.class)
    )
    @GetMapping
    public ResponseItem<GatewayRoute> getByUri(@RequestParam String uri) {
        return ok(gatewayRoutService.query(new GatewayRouteQueryParam(uri)).stream().findFirst().orElse(null));
    }

    @ApiOperation(value = "搜索网关路由", notes = "根据条件查询网关路由信息")
    @ApiImplicitParam(name = "gatewayRoutQueryForm", value = "网关路由查询参数", required = true, dataType = "GatewayRouteQueryForm")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = ResponseList.class)
    )
    @PostMapping(value = "/conditions")
    public ResponseList<GatewayRoute> search(@Valid @RequestBody GatewayRouteQueryParam gatewayRouteQueryForm) {
        return okList(gatewayRoutService.query(gatewayRouteQueryForm));
    }

    @ApiOperation(value = "重载网关路由", notes = "将所以网关的路由全部重载到redis中")
    @ApiResponses(
        @ApiResponse(code = 200, message = "处理成功", response = ResponseItem.class)
    )
    @PostMapping(value = "/overload")
    public ResponseItem<Boolean> overload() {
        return ok();
    }

}