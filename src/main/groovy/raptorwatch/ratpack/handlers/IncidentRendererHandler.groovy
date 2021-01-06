package raptorwatch.ratpack.handlers

import static ratpack.groovy.Groovy.groovyMarkupTemplate

import raptorwatch.IncidentRecord
import ratpack.exec.Blocking
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler

class IncidentRendererHandler extends GroovyHandler {
    IncidentRendererHandler() {
    }

    @Override
    protected void handle(GroovyContext context) {
        context.with {
            def record = get(IncidentRecord)
            Blocking.get({ record.incidentCount })
                    .then({ render(groovyMarkupTemplate("index.gtpl", count: it)) })
        }
    }
}
