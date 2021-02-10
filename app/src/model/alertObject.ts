export default class AlertObject {
  private readonly _type: string
  private readonly _message: string
  private readonly _enableServerReload: boolean

  constructor(type: string, message: string, enableServerReload: boolean) {
    this._type = type
    this._message = message
    this._enableServerReload = enableServerReload
  }

  get type(): string {
    return this._type
  }

  get message(): string {
    return this._message
  }

  get enableServerReload(): boolean {
    return this._enableServerReload
  }
}
