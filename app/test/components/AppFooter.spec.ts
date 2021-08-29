import { createLocalVue, mount } from '@vue/test-utils'

import Vuetify from 'vuetify'

import AppFooter from '~/components/AppFooter.vue'

describe('AppFooter', () => {
  const localVue = createLocalVue()
  let vuetify: Vuetify

  beforeEach(() => {
    vuetify = new Vuetify()
  })

  test('is a Vue instance', () => {
    const wrapper = mount(AppFooter, { localVue, vuetify })
    expect(wrapper.vm).toBeTruthy()
  })

  test('renders correctly', () => {
    const wrapper = mount(AppFooter, { localVue, vuetify })
    expect(wrapper.html()).toMatchSnapshot()
  })
})
