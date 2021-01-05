package raptorwatch.ratpack.handlers

import raptorwatch.IncidentRecord
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler

class IncidentReportHandler extends GroovyHandler {
    IncidentReportHandler() {
    }

    @Override
    protected void handle(GroovyContext context) {
        def record = context.get(IncidentRecord)
        record.incidentCount++;
        def response = context.response
        response.status(204)
        response.send()
    }
}
