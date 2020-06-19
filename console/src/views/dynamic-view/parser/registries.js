
/**
 * 视图仓库
 */
class ViewportRegistry {
  templates = {}

  regist(name, device, version, template) {
    const key = this._wrapKey(name, device, version)
    if (!this.templates[key]) {
      this.templates[key] = template
    }
  }

  getViewport(name, device, version) {
    return this.templates[this._wrapKey(name, device, version)]
  }

  _wrapKey(name, device, version) {
    console.log(':>>>:::', `${name}-${device}-${version}`)
    return `${name}-${device}-${version}`
  }
}

class CommandRegistry {
  cmds = {}

  regist(name, cmd) {
    if (!this.cmds[name]) {
      this.cmds[name] = cmd
    }
  }

  getCommand(name) {
    return this.cmds[name]
  }
}

export const viewportRegistry = new ViewportRegistry()
export const commandRegistry = new CommandRegistry()
