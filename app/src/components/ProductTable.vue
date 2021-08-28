<template>
  <v-data-table
    :footer-props="footerProps"
    :headers="headers"
    :items="items"
    disable-filtering
    disable-sort
  >
    <template #item.colors="{ item }">
      <ColorsItem :value="item.colors" />
    </template>

    <template #item.availability="{ item }">
      <AvailabilityChip :value="item.availability" />
    </template>
  </v-data-table>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'nuxt-property-decorator'

import AvailabilityChip from '~/components/AvailabilityChip.vue'
import ColorsItem from '~/components/ColorsItem.vue'

import Product from '~/model/product'

@Component({
  components: { ColorsItem, AvailabilityChip },
})
export default class ProductTable extends Vue {
  @Prop()
  items!: Array<Product>

  footerProps = {
    itemsPerPageOptions: [5, 10, 15, 25, 50, -1],
  }

  headers = [
    {
      text: 'Id',
      value: 'id',
      width: '20%',
      divider: true,
    },
    {
      text: 'Name',
      value: 'name',
      width: '25%',
      divider: true,
    },
    {
      text: 'Manufacturer',
      value: 'manufacturer',
      width: '15%',
      divider: true,
    },
    {
      text: 'Price',
      value: 'price',
      width: '10%',
      divider: true,
    },
    {
      text: 'Color(s)',
      value: 'colors',
      width: '20%',
      divider: true,
    },
    {
      text: 'Availability',
      value: 'availability',
      width: '10%',
    },
  ]
}
</script>
