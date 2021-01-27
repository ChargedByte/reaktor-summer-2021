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
export default class EmptyLayout extends Vue {}
</script>

<style lang="scss">
html {
  overflow-y: auto;
}
</style>
