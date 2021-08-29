import { createLocalVue, mount } from '@vue/test-utils'

import Vuetify from 'vuetify'

import AvailabilityChip from '~/components/AvailabilityChip.vue'

describe('AvailabilityChip', () => {
  const localVue = createLocalVue()
  let vuetify: Vuetify

  beforeEach(() => {
    vuetify = new Vuetify()
  })

  test('is a Vue instance', () => {
    const wrapper = mount(AvailabilityChip, { localVue, vuetify })
    expect(wrapper.vm).toBeTruthy()
  })

  test('renders correctly: empty', () => {
    const wrapper = mount(AvailabilityChip, { localVue, vuetify })
    expect(wrapper.html()).toMatchSnapshot()
  })

  test('renders correctly: InStock', () => {
    const wrapper = mount(AvailabilityChip, {
      localVue,
      vuetify,
      propsData: { value: 'InStock' },
    })
    expect(wrapper.html()).toMatchSnapshot()
  })

  test('renders correctly: LessThan10', () => {
    const wrapper = mount(AvailabilityChip, {
      localVue,
      vuetify,
      propsData: { value: 'LessThan10' },
    })
    expect(wrapper.html()).toMatchSnapshot()
  })

  test('renders correctly: OutOfStock', () => {
    const wrapper = mount(AvailabilityChip, {
      localVue,
      vuetify,
      propsData: { value: 'OutOfStock' },
    })
    expect(wrapper.html()).toMatchSnapshot()
  })
})
