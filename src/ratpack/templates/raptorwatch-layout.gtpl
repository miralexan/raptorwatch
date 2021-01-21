yieldUnescaped '<!DOCTYPE html>'
html {
  head {
    meta(charset:'utf-8')
    title("RaptorWatch")

    meta(name: 'apple-mobile-web-app-title', content: 'RaptorWatch')
    meta(name: 'description', content: '')
    meta(name: 'viewport', content: 'width=device-width, initial-scale=1')

    link(rel: 'stylesheet', href: 'style.css')
    yieldUnescaped '''
       <script type="module">
         import hotwiredTurbo from 'https://cdn.skypack.dev/@hotwired/turbo';
       </script>
       '''
  }
  body(class: 'root') {
    header(class: 'header') {
      h1 'RaptorWatch'
      h2 'Sneaky Sneaky Dinosaurs'
    }

    bodyContents()

    footer(class: 'footer') {}
  }
}
