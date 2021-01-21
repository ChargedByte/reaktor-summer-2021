import { Module, VuexModule } from 'nuxt-property-decorator'

@Module({
  name: 'modules/general',
  stateFactory: true,
  namespaced: true,
})
export default class General extends VuexModule {}
