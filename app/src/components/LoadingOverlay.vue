<template>
  <v-overlay :value="value" opacity="1">
    <v-progress-circular color="primary" indeterminate size="64" />
  </v-overlay>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'nuxt-property-decorator'

@Component
export default class LoadingOverlay extends Vue {
  @Prop()
  value!: boolean
}
</script>
