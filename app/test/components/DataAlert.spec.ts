import { createLocalVue, mount } from '@vue/test-utils'

import Vuetify from 'vuetify'

import DataAlert from '~/components/DataAlert.vue'
import AlertObject from '~/model/alertObject'

describe('DataAlert', () => {
  const localVue = createLocalVue()
  let vuetify: Vuetify

  beforeEach(() => {
    vuetify = new Vuetify()
  })

  test('is a Vue instance', () => {
    const object = new AlertObject('info', '', false)

    const wrapper = mount(DataAlert, {
      localVue,
      vuetify,
      propsData: { object },
    })
    expect(wrapper.vm).toBeTruthy()
  })

  test('renders correctly: warning', () => {
    const object = new AlertObject('warning', 'Test with warning type', false)

    const wrapper = mount(DataAlert, {
      localVue,
      vuetify,
      propsData: { object },
    })
    expect(wrapper.html()).toMatchSnapshot()
  })

  test('renders correctly: warning + reload', () => {
    const object = new AlertObject('warning', 'Test with warning type', true)

    const wrapper = mount(DataAlert, {
      localVue,
      vuetify,
      propsData: { object },
    })
    expect(wrapper.html()).toMatchSnapshot()
  })

  test('renders correctly: error', () => {
    const object = new AlertObject('error', 'Test with error type', false)

    const wrapper = mount(DataAlert, {
      localVue,
      vuetify,
      propsData: { object },
    })
    expect(wrapper.html()).toMatchSnapshot()
  })

  test('renders correctly: error + reload', () => {
    const object = new AlertObject('error', 'Test with error type', true)

    const wrapper = mount(DataAlert, {
      localVue,
      vuetify,
      propsData: { object },
    })
    expect(wrapper.html()).toMatchSnapshot()
  })
})
