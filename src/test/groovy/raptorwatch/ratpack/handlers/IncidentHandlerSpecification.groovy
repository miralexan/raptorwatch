package raptorwatch.ratpack.handlers

import static ratpack.groovy.test.handling.GroovyRequestFixture.handle

import raptorwatch.IncidentRecord
import ratpack.groovy.template.MarkupTemplate
import ratpack.http.Status
import spock.lang.Specification

class IncidentHandlerSpecification extends Specification {

    def 'the handler renders the incident count'() {
        given:
        def record = new IncidentRecord(a)
        when:
        def result = handle(new IncidentHandler()) {
            registry {
                add(record)
            }
            method 'get'
        }
        then:
        result.status == Status.OK
        def template = result.rendered(MarkupTemplate)
        template.name == 'frame.gtpl'
        template.model == [count: a]
        where:
        a << (0..10)
    }

    def 'the handler increments the incident count'() {
        given:
        def record = new IncidentRecord(a)
        when:
        handle(new IncidentHandler()) {
            registry {
                add(record)
            }
            method 'post'
        }
        then:
        record.incidentCount == a + 1
        where:
        a << (0..10)
    }

    def 'the handler returns a 303 response'() {
        given:
        def record = new IncidentRecord()
        when:
        def result = handle(new IncidentHandler()) {
            registry {
                add(record)
            }
            method 'post'
        }
        then:
        result.with {
            status == Status.of(303)
            bodyText == ''
            headers.get('Location').endsWith('/incidents')
        }
    }
}
