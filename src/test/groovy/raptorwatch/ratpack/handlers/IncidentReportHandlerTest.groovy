package raptorwatch.ratpack.handlers

import static ratpack.groovy.test.handling.GroovyRequestFixture.handle

import raptorwatch.IncidentRecord
import ratpack.http.Status
import spock.lang.Specification

class IncidentReportHandlerTest extends Specification {
    def 'the handler increments the incident count'() {
        given:
        def record = new IncidentRecord(a)
        when:
        handle(new IncidentReportHandler()) {
            registry {
                add(record)
            }
        }
        then:
        record.incidentCount == a + 1
        where:
        a << (0..10)
    }

    def 'the handler returns a 204 response'() {
        given:
        def record = new IncidentRecord()
        when:
        def result = handle(new IncidentReportHandler()) {
            registry {
                add(record)
            }
        }
        then:
        result.status == Status.NO_CONTENT
        result.bodyText == ''
    }
}
