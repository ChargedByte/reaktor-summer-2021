import { Store } from 'vuex'
import { getModule } from 'nuxt-property-decorator'

import General from '~/store/modules/general'
import Cache from '~/store/modules/cache'

// eslint-disable-next-line import/no-mutable-exports
let generalStore: General
// eslint-disable-next-line import/no-mutable-exports
let cacheStore: Cache

function initialiseStores(store: Store<any>) {
  generalStore = getModule(General, store)
  cacheStore = getModule(Cache, store)
}

export { initialiseStores, generalStore, cacheStore }
