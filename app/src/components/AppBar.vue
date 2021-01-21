<template>
  <v-app-bar app color="primary" dark>
    <v-tabs>
      <v-tab
        v-for="category in categories"
        :key="category"
        :to="'/' + category"
      >
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
import { Component, Inject, Vue } from 'nuxt-property-decorator'

@Component
export default class AppBar extends Vue {
  @Inject() categories!: Array<string>
}
</script>
