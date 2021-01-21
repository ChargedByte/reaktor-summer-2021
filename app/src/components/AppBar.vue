<template>
  <v-app-bar app color="primary" dark>
    <v-tabs v-model="tab">
      <v-tab v-for="category in categories" :key="categories.indexOf(category)">
        {{ category }}
      </v-tab>
    </v-tabs>

    <v-spacer />

    <v-btn disabled icon>
      <v-icon>mdi-magnify</v-icon>
    </v-btn>

    <v-btn disabled icon>
      <v-icon>mdi-filter-variant</v-icon>
    </v-btn>

    <v-btn disabled icon>
      <v-icon>mdi-cog</v-icon>
    </v-btn>
  </v-app-bar>
</template>

<script lang="ts">
import { Component, Vue, Watch } from 'nuxt-property-decorator'

import { categories } from '~~/categories.json'

@Component
export default class AppBar extends Vue {
  tab = 0

  categories: Array<string> = categories

  @Watch('tab')
  onTabChanged() {
    this.$emit('tabChanged', this.tab)
  }
}
</script>
