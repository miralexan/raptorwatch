layout 'raptorwatch-layout.gtpl',
  bodyContents: contents {
    'turbo-frame'(id: 'incident-count', class: 'container content', src: '/incidents'){
      section(class: 'count') {
        p 'loading'
      }
    }
  }
