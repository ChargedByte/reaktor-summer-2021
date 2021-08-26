module.exports = () => {
  return {
    require: ['./test/helpers/ava.setup.js'],
    ignoredByWatcher: ['!**/*.{js,ts,vue}'],
    babel: true,
    tap: true,
    verbose: true,
    color: true
  }
}
