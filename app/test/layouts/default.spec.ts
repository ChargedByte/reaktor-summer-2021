import { createLocalVue, mount, RouterLinkStub } from '@vue/test-utils'

import Vuetify from 'vuetify'

import DefaultLayout from '~/layouts/default.vue'

describe('DefaultLayout', () => {
  const localVue = createLocalVue()
  let vuetify: Vuetify

  beforeEach(() => {
    vuetify = new Vuetify()
  })

  test('is a Vue instance', () => {
    const wrapper = mount(DefaultLayout, {
      localVue,
      vuetify,
      stubs: { RouterLink: RouterLinkStub, Nuxt: { template: '<div />' } },
    })
    expect(wrapper.vm).toBeTruthy()
  })

  test('renders correctly', () => {
    const wrapper = mount(DefaultLayout, {
      localVue,
      vuetify,
      stubs: { RouterLink: RouterLinkStub, Nuxt: { template: '<div />' } },
    })
    expect(wrapper.html()).toMatchSnapshot()
  })
})
