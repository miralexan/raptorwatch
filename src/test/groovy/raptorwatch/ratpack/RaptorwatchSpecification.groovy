package raptorwatch.ratpack

import static org.hamcrest.text.StringContainsInOrder.stringContainsInOrder
import static spock.util.matcher.HamcrestSupport.expect

import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.http.HttpUrlBuilder
import ratpack.http.Status
import ratpack.test.ServerBackedApplicationUnderTest
import ratpack.test.http.TestHttpClient
import spock.lang.Specification

class RaptorwatchSpecification extends Specification {

    ServerBackedApplicationUnderTest aut = new GroovyRatpackMainApplicationUnderTest()
    @Delegate
    TestHttpClient client = aut.httpClient

    def cleanup() {
        aut.close()
    }

    def 'retrieving the default page at the root resource'() {
        when:
        get()

        then:
        response.status == Status.OK
        String body = response.body.text
        expect body, stringContainsInOrder('<title>RaptorWatch</title>', 'loading')
    }
    
    def 'retrieving the style.css page at the root resource'() {
        when:
        get('style.css')

        then:
        response.status == Status.OK
        response.body.text == getResource("public/style.css")
    }

    def 'retrieving the page at the /incidents resource'() {
        when:
        get('/incidents')

        then:
        response.status == Status.OK
        String body = response.body.text
        expect body, stringContainsInOrder('<title>RaptorWatch</title>', 'There have been', '0',
                'recorded velociraptor attacks')
    }

    def 'posting to the /incidents resource'() {
        when:
        post('/incidents')

        then:
        response.statusCode == 303
        response.body.text.isEmpty()
        response.headers.getAll('Location') == [buildUri(aut.address, '/incidents')]
    }

    def 'posting to the /incidents resource, then accessing the page at the /incidents resource'() {
        when:
        post('/incidents')
        get('/incidents')

        then:
        response.statusCode == 200
        String body = response.body.text
        expect body, stringContainsInOrder('<title>RaptorWatch</title>', 'There have been', '1',
                'recorded velociraptor attacks')
    }

    private static String buildUri(URI base, String... pathSegments) {
        def builder = HttpUrlBuilder.base(base)
        pathSegments.each {
            builder.path(it)
        }
        builder.build().toString()
    }

    private static String getResource(String path) {
        def classLoader = Thread.currentThread().getContextClassLoader();

        return classLoader.getResourceAsStream(path).getText("utf-8")
    }
}
