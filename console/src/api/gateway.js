import request from '@/utils/request'
/**
 * 网关路由服务
 */

const uri_base = `${process.env.VUE_APP_GATEWAY_BASE_API}/gateway/routes`

/**
 * 搜索网关路由
 * @param {*} search
 */
export function searchRoutes(data) {
  return request.post(`${uri_base}/conditions`, data)
}

/**
 * 新增网关路由
 * @param {*} data
 */
export function addRoute(data) {
  return request.post(`${uri_base}`, data)
}

/**
 * 删除网关路由
 */
export function deleteRoute(id) {
  return request.delete(`${uri_base}/${id}`)
}

/**
 * 修改网关路由
 * @param string id 网关路由ID
 * @param {*} data 修改指定网关路由信息
 */
export function updateRoute(id, data) {
  return request.put(`${uri_base}/${id}`, data)
}

/**
 * 获取网关路由
 * @param string id  网关路由ID
 */
export function getRoute(id) {
  return request.get(`${uri_base}/${id}`)
}

/**
 * 根据uri获取网关路由
 * @param string uri  网关路由路径
 */
export function getByUri(uri) {
  return request.get(`${uri_base}?uri=${uri}`)
}

/**
 * 重载网关路由,将所以网关的路由全部重载到redis中
 * @param string uri  网关路由路径
 */
export function overload(uri) {
  return request.post(`${uri_base}/overload`)
}
