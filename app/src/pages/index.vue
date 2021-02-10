<template>
  <v-container>
    <LoadingOverlay :value="!ready" />
  </v-container>
</template>

<script lang="ts">
import { Context } from '@nuxt/types'

import { Component, Vue } from 'nuxt-property-decorator'

import LoadingOverlay from '~/components/LoadingOverlay.vue'

import { generalStore } from '~/store'

import { categories } from '~~/categories.json'

interface StatusObject {
  status: string
}

@Component({
  components: { LoadingOverlay },
  layout: 'empty',
})
export default class IndexPage extends Vue {
  timer: NodeJS.Timeout | undefined

  get ready() {
    return generalStore.loadingComplete
  }

  updated() {
    if (this.ready) {
      if (typeof this.timer !== 'undefined') {
        clearInterval(this.timer)
      }

      this.$router.push({ path: '/' + categories[0] })
    }
  }

  created() {
    if (!this.ready && typeof this.timer === 'undefined') {
      this.timer = setInterval(this.$nuxt.refresh, 1000)
    }

    if (this.ready) {
      this.$router.push({ path: '/' + categories[0] })
    }
  }

  async asyncData(ctx: Context) {
    if (!generalStore.loadingComplete) {
      const object: StatusObject = await ctx.$http.$get('/api/status')

      const ready = object.status === 'ready'
      if (ready) generalStore.toggleLoadingComplete()
    }
  }
}
</script>
