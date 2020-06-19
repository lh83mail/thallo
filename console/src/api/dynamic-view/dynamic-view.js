import request from '@/utils/request'

/**
 * 读取视图配置
 * @param {string} viewId
 */
export function loadViewConfig(viewId, device = 'PC', version = '1.0') {
  return request.get(`${process.env.VUE_APP_GATEWAY_BASE_API}/dyn-view/definitions/${viewId}/${version}/${device}`)
    .then(res => res.data)
}
