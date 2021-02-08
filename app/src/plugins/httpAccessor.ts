import { Plugin } from '@nuxt/types'

import { initializeHttp } from '~/utils/http'

const accessor: Plugin = (ctx) => {
  initializeHttp(ctx.$http)
}

export default accessor
