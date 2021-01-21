layout 'raptorwatch-layout.gtpl',
  bodyContents: contents {
    'turbo-frame'(id: 'incident-count', class: 'container content'){
      section(class: 'prefix') {
        p 'There have been'
      }
      section(class: 'count') {
        p count
      }
      section(class: 'suffix') {
        p 'recorded velociraptor attacks'
        form(action: '/incidents', method: 'post') {
          input(type: 'submit', value: 'Report Incident')
        }
      }
    }
  }
