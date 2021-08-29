import { createLocalVue, mount, RouterLinkStub } from '@vue/test-utils'

import Vuetify from 'vuetify'

import AppBar from '~/components/AppBar.vue'

describe('AppBar', () => {
  const localVue = createLocalVue()
  let vuetify: Vuetify

  beforeEach(() => {
    vuetify = new Vuetify()
  })

  test('is a Vue instance', () => {
    const wrapper = mount(AppBar, {
      localVue,
      vuetify,
      stubs: { RouterLink: RouterLinkStub },
    })
    expect(wrapper.vm).toBeTruthy()
  })

  test('renders correctly', () => {
    const wrapper = mount(AppBar, {
      localVue,
      vuetify,
      stubs: { RouterLink: RouterLinkStub },
    })
    expect(wrapper.html()).toMatchSnapshot()
  })
})
