package raptorwatch.ratpack.handlers

import raptorwatch.IncidentRecord
import ratpack.exec.Blocking
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler

class IncidentReportHandler extends GroovyHandler {
    IncidentReportHandler() {
    }

    @Override
    protected void handle(GroovyContext context) {
        def record = context.get(IncidentRecord)
        def response = context.response
        Blocking.get({ record.incidentCount++ })
                .then({
                    response.status(204)
                    response.send()
                })
    }
}
