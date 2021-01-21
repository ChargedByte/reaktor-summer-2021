import { Plugin } from '@nuxt/types'

const nuxtClientInit: Plugin = async (ctx) => {
  await ctx.store.dispatch('nuxtClientInit', ctx)
}

export default nuxtClientInit
