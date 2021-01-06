package raptorwatch.ratpack.handlers

import static ratpack.groovy.test.handling.GroovyRequestFixture.handle

import raptorwatch.IncidentRecord
import ratpack.groovy.template.MarkupTemplate
import ratpack.http.Status
import spock.lang.Specification

class IncidentRendererHandlerTest extends Specification {
    def 'the handler renders the incident count'() {
        given:
        def record = new IncidentRecord(a)
        when:
        def result = handle(new IncidentRendererHandler()) {
            registry {
                add(record)
            }
        }
        then:
        result.status == Status.OK
        def template = result.rendered(MarkupTemplate)
        template.name == 'index.gtpl'
        template.model == [count: a]
        where:
        a << (0..10)
    }
}
