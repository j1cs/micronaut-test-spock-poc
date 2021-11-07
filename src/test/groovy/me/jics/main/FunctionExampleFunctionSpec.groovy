package me.jics.main

import groovy.util.logging.Slf4j
import io.micronaut.http.*
import io.micronaut.gcp.function.http.*
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.*

@Slf4j
@MicronautTest
class FunctionExampleFunctionSpec extends Specification {

    @Shared @AutoCleanup
    HttpFunction function = new HttpFunction()

    @Inject
    FunctionService functionService

    void "test post function"() {
        given:"The request"
        SampleInputMessage sampleInputMessage = new SampleInputMessage("Test Name")

        when:"The POST function is executed"
        HttpRequest request = HttpRequest.POST("/functionExample", sampleInputMessage).contentType(MediaType.APPLICATION_JSON_TYPE)
        GoogleHttpResponse response = function.invoke(request)

        then:"The response is correct"
        1 * functionService.process(sampleInputMessage) >> 1
        response.status == HttpStatus.OK
    }

    @MockBean(FunctionServiceImpl)
    FunctionService functionService() {
        Mock(FunctionService)
    }

    /*@Replaces(SimpleClient)
    @Singleton
    static class MockSimpleClient implements SimpleClient {
        @Override
        int sent(SampleInputMessage inputMessage) {
            return 1
        }
    }*/
}
