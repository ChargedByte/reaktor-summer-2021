import { createLocalVue, mount } from '@vue/test-utils'

import Vuetify from 'vuetify'

import EmptyLayout from '~/layouts/empty.vue'

describe('EmptyLayout', () => {
  const localVue = createLocalVue()
  let vuetify: Vuetify

  beforeEach(() => {
    vuetify = new Vuetify()
  })

  test('is a Vue instance', () => {
    const wrapper = mount(EmptyLayout, {
      localVue,
      vuetify,
      stubs: { Nuxt: { template: '<div />' } },
    })
    expect(wrapper.vm).toBeTruthy()
  })

  test('renders correctly', () => {
    const wrapper = mount(EmptyLayout, {
      localVue,
      vuetify,
      stubs: { Nuxt: { template: '<div />' } },
    })
    expect(wrapper.html()).toMatchSnapshot()
  })
})
