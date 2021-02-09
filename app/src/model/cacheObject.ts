import Product from '~/model/product'

export default class CacheObject {
  private readonly _category: string
  private readonly _lastModified: Date
  private readonly _products: Array<Product>

  constructor(category: string, lastModified: Date, products: Array<Product>) {
    this._category = category
    this._lastModified = lastModified
    this._products = products
  }

  get lastModified(): Date {
    return this._lastModified
  }

  get products(): Array<Product> {
    return this._products
  }

  get category(): string {
    return this._category
  }
}
