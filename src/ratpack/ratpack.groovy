import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack

import raptorwatch.IncidentRecord
import raptorwatch.ratpack.handlers.IncidentHandler
import ratpack.groovy.template.MarkupTemplateModule

ratpack {
    serverConfig {
        development true
    }

    bindings {
        module MarkupTemplateModule
    }

    handlers {
        register {
            add new IncidentRecord()
        }

        path('incidents') {
            insert new IncidentHandler()
        }

        get {
            render(groovyMarkupTemplate("index.gtpl"))
        }

        files {
            dir 'public'
        }
    }
}
