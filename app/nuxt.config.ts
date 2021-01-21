import { NuxtConfig } from '@nuxt/types'

const isDev = process.env.NODE_ENV === 'development'

const config: NuxtConfig = {
  srcDir: 'src',

  // Target: https://go.nuxtjs.dev/config-target
  target: 'static',

  // Server-side rendering: https://go.nuxtjs.dev/config-ssr
  ssr: false,

  // Modern (https://nuxtjs.org/docs/2.x/configuration-glossary/configuration-modern)
  modern: isDev ? false : 'client',

  // Global page headers: https://go.nuxtjs.dev/config-head
  head: {
    titleTemplate: 'WiM',
    htmlAttrs: {
      lang: 'en',
    },
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1.0' },
    ],
    link: [],
  },

  // Global CSS: https://go.nuxtjs.dev/config-css
  css: [],

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: [{ src: '~/plugins/nuxtClientInit', mode: 'client' }],

  // Auto import components: https://go.nuxtjs.dev/config-components
  components: true,

  // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
  buildModules: [
    // https://go.nuxtjs.dev/typescript
    '@nuxt/typescript-build',
    // https://go.nuxtjs.dev/stylelint
    '@nuxtjs/stylelint-module',
    // https://go.nuxtjs.dev/vuetify
    '@nuxtjs/vuetify',
  ],

  // Modules: https://go.nuxtjs.dev/config-modules
  modules: [
    // https://http.nuxtjs.org/
    '@nuxt/http',
  ],

  // Vuetify module configuration: https://go.nuxtjs.dev/config-vuetify
  vuetify: {
    customVariables: ['~/assets/variables.scss'],
  },

  // Http module configuration (https://http.nuxtjs.org/options)
  http: {},

  // Build Configuration: https://go.nuxtjs.dev/config-build
  build: {},
}

export default config
