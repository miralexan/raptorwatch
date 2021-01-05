yieldUnescaped '<!DOCTYPE html>'
html {
  head {
    meta(charset:'utf-8')
    title("RaptorWatch")

    meta(name: 'apple-mobile-web-app-title', content: 'RaptorWatch')
    meta(name: 'description', content: '')
    meta(name: 'viewport', content: 'width=device-width, initial-scale=1')

    link(rel: 'stylesheet', href: 'style.css')
  }
  body(class: 'container') {
    header(class: 'header') {
      h1 'RaptorWatch'
      h2 'Sneaky Sneaky Dinosaurs'
    }

    section(class: 'prefix') {
      p 'There have been'
    }

    section(class: 'count') {
      p count
    }

    section(class: 'suffix') {
      p 'recorded velociraptor attacks'
    }

    footer(class: 'footer') {}
  }
}
