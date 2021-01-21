package raptorwatch.ratpack.handlers

import static ratpack.groovy.Groovy.groovyMarkupTemplate

import raptorwatch.IncidentRecord
import ratpack.exec.Blocking
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler

class IncidentHandler extends GroovyHandler {
    IncidentHandler() {
    }

    @Override
    protected void handle(GroovyContext context) {
        context.with {
            def record = get(IncidentRecord)
            byMethod {
                get {
                    Blocking.get({ record.incidentCount })
                            .then({ render(groovyMarkupTemplate("frame.gtpl", count: it)) })
                }

                post {
                    Blocking.get({ record.incidentCount++ })
                            .then({
                                redirect(303, '/incidents')
                            })
                }
            }
        }
    }
}
