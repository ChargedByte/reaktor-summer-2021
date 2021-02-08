<template>
  <span>{{ content }}</span>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'nuxt-property-decorator'

import { capitalizeFirstLetter } from '~/utils/stringUtil'

@Component
export default class ColorsItem extends Vue {
  @Prop()
  value!: Array<string>

  get content() {
    return this.value.map(capitalizeFirstLetter).join(', ')
  }
}
</script>
