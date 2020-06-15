import request from '@/utils/request'

/**
 * 读取视图配置
 * @param {string} viewId
 */
export function loadViewConfig(viewId, device, version) {
  return request.get(`/dynamic-view/view-config/${viewId}`).then(res => res.data)
}
