package me.jics.main;

import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.MediaType;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Controller("/functionExample")
public class FunctionExampleController {

    private final FunctionService functionService;

    public FunctionExampleController(FunctionService functionService) {
        this.functionService = functionService;
    }


    @Post
    public SampleReturnMessage postMethod(@Body SampleInputMessage inputMessage){
      int messageId = this.functionService.process(inputMessage);

      return SampleReturnMessage.builder().returnMessage(String.format("This is you messageId %d, and you sent %s", messageId, inputMessage.getName())).build();
    }
}

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Introspected
class SampleInputMessage{
    private String name;
}

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Introspected
class SampleReturnMessage{
    private String returnMessage;
}
