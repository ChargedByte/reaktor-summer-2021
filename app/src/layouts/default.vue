<template>
  <v-app id="wim">
    <AppBar />

    <v-main>
      <Nuxt />
    </v-main>

    <AppFooter />
  </v-app>
</template>

<script lang="ts">
import { Component, Provide, Vue } from 'nuxt-property-decorator'

import AppBar from '~/components/AppBar.vue'
import AppFooter from '~/components/AppFooter.vue'

import { categories } from '~~/categories.json'

@Component({
  components: { AppBar, AppFooter },
})
export default class DefaultLayout extends Vue {
  @Provide() categories = categories
}
</script>

<style lang="scss">
html {
  overflow-y: auto;
}
</style>
