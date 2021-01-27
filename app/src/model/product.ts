export enum Category {
  Unknown,
  Beanies,
  Facemasks,
  Gloves,
}

export enum Availability {
  Unknown,
  InStock,
  LessThan10,
  OutOfStock,
}

export default interface Product {
  id: string
  category: Category
  name: string
  colors: Array<string>
  price: number
  manufacturer: string
  availability: Availability
}
