export default interface ErrorState {
  failedCategories: Array<string>
  failedManufacturers: Array<string>
  productsTasksInterrupted: boolean
  availabilityTasksInterrupted: boolean
  productConversionTasksFailed: boolean
}
