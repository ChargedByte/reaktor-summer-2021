<template>
  <v-footer app>
    <v-spacer />

    <v-btn href="https://github.com/ChargedByte/WiM" icon target="_blank">
      <v-icon>mdi-github</v-icon>
    </v-btn>
  </v-footer>
</template>

<script lang="ts">
import { Component, Vue } from 'nuxt-property-decorator'

@Component
export default class AppFooter extends Vue {}
</script>
