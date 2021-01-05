import static ratpack.groovy.Groovy.ratpack

import raptorwatch.IncidentRecord
import raptorwatch.ratpack.handlers.IncidentRendererHandler
import raptorwatch.ratpack.handlers.IncidentReportHandler
import ratpack.groovy.template.MarkupTemplateModule

ratpack {
    bindings {
        module MarkupTemplateModule
    }

    handlers {
        register {
            add new IncidentRecord()
        }
        get {
            byContent {
                html {
                    insert new IncidentRendererHandler()
                }
            }
        }

        post('report') {
            insert new IncidentReportHandler()
        }

        files {
            dir 'public'
        }
    }
}
