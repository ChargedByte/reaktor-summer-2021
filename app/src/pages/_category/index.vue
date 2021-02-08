<template>
  <v-container fill-height fluid>
    <v-row class="fill-height" no-gutters>
      <v-col>
        <v-card class="fill-height" flat>
          <v-card-subtitle>
            <DataAlert
              v-for="error in $data.errors"
              :key="$data.errors.indexOf(error)"
              :object="error"
            />
          </v-card-subtitle>
          <v-card-text>
            <ProductTable :items="$data.products" />
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import { Context } from '@nuxt/types'

import { Component, Vue } from 'nuxt-property-decorator'
import { Route } from 'vue-router'

import DataAlert from '~/components/DataAlert.vue'
import ProductTable from '~/components/ProductTable.vue'

import AlertObject from '~/model/alertObject'
import CacheObject from '~/model/cacheObject'
import ErrorState from '~/model/errorState'
import Product from '~/model/product'

import { cacheStore, generalStore } from '~/store'
import { capitalizeFirstLetter } from '~/utils/stringUtil'

import { categories } from '~~/categories.json'

@Component({
  components: { DataAlert, ProductTable },
  head() {
    const title = capitalizeFirstLetter(this.$route.params.category)
    return { title }
  },
})
export default class CategoryPage extends Vue {
  timer: NodeJS.Timeout | undefined

  beforeRouteEnter(_to: Route, _from: Route, next: Function) {
    // if the app hasn't been loaded, go make sure it's ready to be used
    if (!generalStore.loadingComplete) {
      next({ path: '/' })
      return
    }
    next()
  }

  // only accept valid categories
  validate(ctx: Context) {
    const category = ctx.route.params.category
    return categories.includes(category)
  }

  created() {
    if (typeof this.timer === 'undefined') {
      this.timer = setInterval(this.$nuxt.refresh, 1000 * 5)
    }
  }

  beforeDestroy() {
    if (typeof this.timer !== 'undefined') {
      clearInterval(this.timer)
    }
  }

  async asyncData(ctx: Context) {
    const category = ctx.route.params.category

    // <editor-fold desc="Fetch ErrorState">
    const errors: Array<AlertObject> = []

    try {
      const errorState: ErrorState = await ctx.$http.$get('/api/errors')

      if (errorState.failedCategories.length > 0) {
        errors.push(
          new AlertObject(
            'error',
            `Failed to load products: ${errorState.failedCategories.join(
              ', '
            )}`,
            true
          )
        )
      }

      if (errorState.failedManufacturers.length > 0) {
        errors.push(
          new AlertObject(
            'error',
            `Failed to load availabilities: ${errorState.failedManufacturers.join(
              ', '
            )}`,
            true
          )
        )
      }

      if (
        errorState.productsTasksInterrupted ||
        errorState.availabilityTasksInterrupted ||
        errorState.productConversionTasksFailed
      ) {
        errors.push(
          new AlertObject(
            'warning',
            'Found interrupted tasks, some data could be incorrect',
            true
          )
        )
      }
    } catch (err) {
      errors.push(
        new AlertObject(
          'error',
          'Failed to load errors. You can try reloading the page.',
          false
        )
      )
    }

    // </editor-fold>

    // <editor-fold desc="Fetch Products">
    // readonly
    const object = cacheStore.get(category)

    let headers = {}
    if (typeof object !== 'undefined') {
      headers = {
        'If-Modified-Since': object.lastModified.toUTCString(),
      }
    }

    try {
      const res = await ctx.$http.get('/api/products/' + category, {
        headers,
      })

      const header = res.headers.get('Last-Modified')

      // if the request is successful header should be present
      if (header != null) {
        const lastModified = new Date(header)

        if (res.status === 200) {
          const products: Array<Product> = await res.json()

          cacheStore.set(new CacheObject(category, lastModified, products))

          return { products, errors }
        }
      }
    } catch (err) {
      const res = err.response

      // object should never be undefined if response is 304
      if (res.status === 304 && typeof object !== 'undefined') {
        const header = res.headers.get('Last-Modified')

        // header should always be present if the response is 304
        if (header != null) {
          const lastModified = new Date(header)

          const products = object.products

          if (object.lastModified !== lastModified) {
            cacheStore.set(new CacheObject(category, lastModified, products))
          }

          cacheStore.set(object)

          return { products, errors }
        }
      }

      const products: Array<Product> = []

      errors.push(
        new AlertObject(
          'error',
          'Failed to load the products. You can try reloading the page.',
          false
        )
      )

      return { products, errors }
    }
    // </editor-fold>
  }
}
</script>
