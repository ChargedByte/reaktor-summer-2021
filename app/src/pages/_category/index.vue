<template>
  <v-container></v-container>
</template>

<script lang="ts">
import { Context } from '@nuxt/types'

import { Component, Vue } from 'nuxt-property-decorator'

import { capitalizeFirstLetter } from '~/utils/stringUtil'

import { categories } from '~~/categories.json'

@Component({
  head() {
    const title = capitalizeFirstLetter(this.$route.params.category)

    return { title }
  },
})
export default class CategoryPage extends Vue {
  validate(ctx: Context) {
    const category = ctx.route.params.category
    return categories.includes(category)
  }

  async asyncData() {}
}
</script>
