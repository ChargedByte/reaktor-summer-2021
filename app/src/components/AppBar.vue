<template>
  <v-app-bar app color="primary" dark dense>
    <v-tabs centered>
      <v-tab
        v-for="category in categories"
        :key="categories.indexOf(category)"
        :to="'/' + category"
      >
        {{ category }}
      </v-tab>
    </v-tabs>
  </v-app-bar>
</template>

<script lang="ts">
import { Component, Vue } from 'nuxt-property-decorator'

import { categories } from '~~/categories.json'

@Component
export default class AppBar extends Vue {
  categories: Array<string> = categories
}
</script>
