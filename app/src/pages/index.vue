<template>
  <v-container>
    <v-row align="center" justify="center">
      <v-overlay :value="loading" opacity="0">
        <v-progress-circular
          color="primary lighten-1"
          indeterminate
          size="96"
        />
      </v-overlay>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import { Context } from '@nuxt/types'

import { Component, Vue } from 'nuxt-property-decorator'

import { categories } from '~~/categories.json'

@Component({
  layout: 'empty',
})
export default class IndexPage extends Vue {
  timer: NodeJS.Timeout | undefined

  refresh() {
    this.$nuxt.refresh()
  }

  updated() {
    if (!this.$data.loading) {
      if (typeof this.timer !== 'undefined') {
        clearInterval(this.timer)
      }

      this.$router.push({ path: '/' + categories[0] })
    }
  }

  created() {
    if (this.$data.loading) {
      this.timer = setInterval(this.refresh, 1000)
    }

    if (!this.$data.loading) {
      this.$router.push({ path: '/' + categories[0] })
    }
  }

  async asyncData(ctx: Context) {
    const response: string = await ctx.$http.$get('/api/is-loading')
    const loading: boolean = JSON.parse(response)

    return { loading }
  }
}
</script>
