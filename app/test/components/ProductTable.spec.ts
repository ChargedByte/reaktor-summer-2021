import { createLocalVue, mount } from '@vue/test-utils'

import Vuetify from 'vuetify'

import ProductTable from '~/components/ProductTable.vue'

import { beanies } from '~~/test/__mocks__/products'

describe('ProductTable', () => {
  const localVue = createLocalVue()
  let vuetify: Vuetify

  beforeEach(() => {
    vuetify = new Vuetify()
  })

  test('is a Vue instance', () => {
    const wrapper = mount(ProductTable, {
      localVue,
      vuetify,
      propsData: { items: [] },
    })
    expect(wrapper.vm).toBeTruthy()
  })

  test('renders correctly', () => {
    const wrapper = mount(ProductTable, {
      localVue,
      vuetify,
      propsData: { items: beanies },
    })
    expect(wrapper.html()).toMatchSnapshot()
  })
})
