import Product from '~/model/product'

export default class CacheObject {
  private readonly _category: string

  constructor(category: string, lastModified: Date, products: Array<Product>) {
    this._category = category
    this._lastModified = lastModified
    this._products = products
  }

  private _lastModified: Date

  get lastModified(): Date {
    return this._lastModified
  }

  set lastModified(value: Date) {
    this._lastModified = value
  }

  private _products: Array<Product>

  get products(): Array<Product> {
    return this._products
  }

  set products(value: Array<Product>) {
    this._products = value
  }

  get category(): string {
    return this._category
  }
}
