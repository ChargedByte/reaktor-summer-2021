import { createLocalVue, mount } from '@vue/test-utils'

import Vuetify from 'vuetify'

import LoadingOverlay from '~/components/LoadingOverlay.vue'

describe('LoadingOverlay', () => {
  const localVue = createLocalVue()
  let vuetify: Vuetify

  beforeEach(() => {
    vuetify = new Vuetify()
  })

  test('is a Vue instance', () => {
    const wrapper = mount(LoadingOverlay, { localVue, vuetify })
    expect(wrapper.vm).toBeTruthy()
  })

  test('renders correctly: on', () => {
    const wrapper = mount(LoadingOverlay, {
      localVue,
      vuetify,
      propsData: { value: true },
    })
    expect(wrapper.html()).toMatchSnapshot()
  })

  test('renders correctly: off', () => {
    const wrapper = mount(LoadingOverlay, {
      localVue,
      vuetify,
      propsData: { value: false },
    })
    expect(wrapper.html()).toMatchSnapshot()
  })
})
