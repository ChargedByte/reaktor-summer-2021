import { Module, VuexModule, VuexMutation } from 'nuxt-property-decorator'

import CacheObject from '~/model/cacheObject'

@Module({
  name: 'modules/cache',
  stateFactory: true,
  namespaced: true,
})
export default class Cache extends VuexModule {
  objects: Array<CacheObject> = []

  get get() {
    return (category: string) =>
      this.objects.find((x) => x.category === category)
  }

  @VuexMutation
  set(value: CacheObject) {
    const index = this.objects.findIndex((x) => x.category === value.category)

    if (index > -1) {
      this.objects[index] = value
      return
    }

    this.objects.push(value)
  }
}
