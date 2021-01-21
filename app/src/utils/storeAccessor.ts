import { Store } from 'vuex'
import { getModule } from 'nuxt-property-decorator'

import General from '~/store/modules/general'

// eslint-disable-next-line import/no-mutable-exports
let generalStore: General

function initialiseStores(store: Store<any>) {
  generalStore = getModule(General, store)
}

export { initialiseStores, generalStore }
