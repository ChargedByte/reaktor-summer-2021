import { createLocalVue, mount } from '@vue/test-utils'

import Vuetify from 'vuetify'

import ColorsItem from '~/components/ColorsItem.vue'

describe('ColorsItem', () => {
  const localVue = createLocalVue()
  let vuetify: Vuetify

  beforeEach(() => {
    vuetify = new Vuetify()
  })

  test('is a Vue instance', () => {
    const wrapper = mount(ColorsItem, {
      localVue,
      vuetify,
      propsData: { value: [] },
    })
    expect(wrapper.vm).toBeTruthy()
  })

  test('renders correctly: empty', () => {
    const wrapper = mount(ColorsItem, {
      localVue,
      vuetify,
      propsData: { value: [] },
    })
    expect(wrapper.html()).toMatchSnapshot()
  })

  test('renders correctly: Red', () => {
    const wrapper = mount(ColorsItem, {
      localVue,
      vuetify,
      propsData: { value: ['red'] },
    })
    expect(wrapper.html()).toMatchSnapshot()
  })

  test('renders correctly: Red, Green', () => {
    const wrapper = mount(ColorsItem, {
      localVue,
      vuetify,
      propsData: { value: ['red', 'Green'] },
    })
    expect(wrapper.html()).toMatchSnapshot()
  })
})
