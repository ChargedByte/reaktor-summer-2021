<template>
  <v-app id="wim">
    <v-main>
      <Nuxt />
    </v-main>
  </v-app>
</template>

<script lang="ts">
import { Component, Vue } from 'nuxt-property-decorator'

@Component
export default class DefaultLayout extends Vue {}
</script>

<style lang="scss">
html {
  overflow-y: auto;
}
</style>
