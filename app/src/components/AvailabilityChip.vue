<template>
  <v-chip :color="color" small>{{ text }}</v-chip>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'nuxt-property-decorator'

@Component
export default class AvailabilityChip extends Vue {
  @Prop()
  value!: string

  get color(): string {
    switch (this.value) {
      case 'InStock':
        return 'success'
      case 'LessThan10':
        return 'warning'
      case 'OutOfStock':
        return 'error'
      default:
        return 'grey'
    }
  }

  get text(): string {
    switch (this.value) {
      case 'InStock':
        return 'In Stock'
      case 'LessThan10':
        return 'Less than 10'
      case 'OutOfStock':
        return 'Out of Stock'
      default:
        return 'Unknown'
    }
  }
}
</script>
