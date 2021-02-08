import { NuxtHTTPInstance } from '@nuxt/http'

// eslint-disable-next-line import/no-mutable-exports
let $http: NuxtHTTPInstance

export function initializeHttp(instance: NuxtHTTPInstance) {
  $http = instance
}

export { $http }
