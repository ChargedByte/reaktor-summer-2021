import { createLocalVue, mount, RouterLinkStub } from '@vue/test-utils'

import Vuetify from 'vuetify'

import ErrorPage from '~/layouts/error.vue'

describe('ErrorPage', () => {
  const localVue = createLocalVue()
  let vuetify: Vuetify

  beforeEach(() => {
    vuetify = new Vuetify()
  })

  test('is a Vue instance', () => {
    const wrapper = mount(ErrorPage, {
      localVue,
      vuetify,
      stubs: { RouterLink: RouterLinkStub },
      propsData: { error: { statusCode: 500 } },
    })
    expect(wrapper.vm).toBeTruthy()
  })

  test('renders correctly: 404', () => {
    const wrapper = mount(ErrorPage, {
      localVue,
      vuetify,
      stubs: { RouterLink: RouterLinkStub },
      propsData: { error: { statusCode: 404 } },
    })
    expect(wrapper.html()).toMatchSnapshot()
  })

  test('renders correctly: 500', () => {
    const wrapper = mount(ErrorPage, {
      localVue,
      vuetify,
      stubs: { RouterLink: RouterLinkStub },
      propsData: { error: { statusCode: 500 } },
    })
    expect(wrapper.html()).toMatchSnapshot()
  })
})
