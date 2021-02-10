<template>
  <v-alert
    v-if="object.enableServerReload"
    :type="object.type"
    border="left"
    dense
    prominent
  >
    <v-row align="center">
      <v-col class="grow">
        {{ object.message }}
      </v-col>
      <v-col class="shrink">
        <v-btn text @click="reload">Reload</v-btn>
      </v-col>
    </v-row>
  </v-alert>

  <v-alert v-else :type="object.type" border="left" dense>
    {{ object.message }}
  </v-alert>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'nuxt-property-decorator'

import AlertObject from '~/model/alertObject'

@Component
export default class DataAlert extends Vue {
  @Prop()
  object!: AlertObject

  async reload() {
    // response could be used to condition
    await this.$http.$post('/api/reload')
  }
}
</script>
