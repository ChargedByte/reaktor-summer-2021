import com.fasterxml.jackson.databind.ObjectMapper;
import dev.chargedbyte.wim.Application;
import dev.chargedbyte.wim.model.AvailabilityResponse;
import dev.chargedbyte.wim.task.ProductUpdateTask;
import mock.MockData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@ActiveProfiles({"test"})
public class ApplicationIntegrationTests {
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private TaskScheduler taskScheduler;

    @SpyBean
    private ProductUpdateTask productUpdateTask;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MockMvc mvc;

    @Test
    public void givenLegacyData_whenGetProducts_thenReturnConvertedProducts() throws Exception {
        MockRestServiceServer mockServer = MockRestServiceServer
            .bindTo(restTemplate)
            .ignoreExpectOrder(true)
            .build();

        // <editor-fold desc="Setup MockServer">
        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/products/beanies")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(MockData.LegacyProducts.beanies)));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/products/facemasks")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(MockData.LegacyProducts.facemasks)));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/products/gloves")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(MockData.LegacyProducts.gloves)));

        // <editor-fold desc="Expect Availabilities">
        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/nascetur")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.nascetur))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/aenean")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.aenean))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/tincidunt")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.tincidunt))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/tortor")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.tortor))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/justo")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.justo))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/ullamcorper")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.ullamcorper))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/interdum")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.interdum))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/orci")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.orci))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/nonummy")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.nonummy))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/condimentum")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.condimentum))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/donec")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.donec))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/praesent")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.praesent))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/luctus")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.luctus))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/vulputate")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.vulputate))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/a")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.a))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/nulla")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.nulla))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/nisi")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.nisi))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/sapien")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.sapien))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/vestibulum")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.vestibulum))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/eget")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.eget))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/feugiat")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.feugiat))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/in")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.in))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/lectus")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.lectus))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/tellus")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.tellus))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/pede")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.pede))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/varius")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.varius))));

        mockServer.expect(ExpectedCount.manyTimes(), requestTo(matchesPattern(".*/availability/mattis")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(new AvailabilityResponse(200, MockData.LegacyAvailabilities.mattis))));

        // </editor-fold>
        // </editor-fold>

        // <editor-fold desc="Expected">
        String jsonBeanies = "[{\"id\":\"R4OuKi9VnVwMKy321ikA\",\"category\":\"Beanies\",\"name\":\"habitasse platea\",\"colors\":[\"Khaki\",\"Fuscia\"],\"price\":638,\"manufacturer\":\"nascetur\",\"availability\":\"InStock\"},{\"id\":\"6rCPg5CcqFJcpAWZDXU0\",\"category\":\"Beanies\",\"name\":\"mattis egestas\",\"colors\":[\"Pink\",\"Fuscia\",\"Crimson\"],\"price\":614,\"manufacturer\":\"aenean\",\"availability\":\"InStock\"},{\"id\":\"GgFnGfzmkZatPp6Sm17p\",\"category\":\"Beanies\",\"name\":\"mauris eget massa\",\"colors\":[\"Purple\"],\"price\":305,\"manufacturer\":\"tincidunt\",\"availability\":\"OutOfStock\"},{\"id\":\"chUDARuvQnB021wTfEeO\",\"category\":\"Beanies\",\"name\":\"libero quis\",\"colors\":[\"Puce\",\"Puce\"],\"price\":528,\"manufacturer\":\"tortor\",\"availability\":\"LessThan10\"},{\"id\":\"DenjHhqoM2UXePNNY0y1\",\"category\":\"Beanies\",\"name\":\"turpis elementum ligula\",\"colors\":[\"Khaki\"],\"price\":426,\"manufacturer\":\"justo\",\"availability\":\"InStock\"},{\"id\":\"1XexnO7fo9opvgtCWAFE\",\"category\":\"Beanies\",\"name\":\"nam dui\",\"colors\":[\"Teal\"],\"price\":161,\"manufacturer\":\"ullamcorper\",\"availability\":\"OutOfStock\"},{\"id\":\"TzpkfyvDkthZmmgq62S6\",\"category\":\"Beanies\",\"name\":\"diam erat\",\"colors\":[\"Puce\",\"Teal\"],\"price\":585,\"manufacturer\":\"interdum\",\"availability\":\"InStock\"},{\"id\":\"iMmLiRxw6tQ3eGeCO9I0\",\"category\":\"Beanies\",\"name\":\"pede posuere\",\"colors\":[\"Mauv\",\"Yellow\"],\"price\":540,\"manufacturer\":\"orci\",\"availability\":\"OutOfStock\"},{\"id\":\"R00IGqYRYA6OEJyzmQOh\",\"category\":\"Beanies\",\"name\":\"volutpat in congue\",\"colors\":[\"Red\",\"Yellow\",\"Violet\"],\"price\":148,\"manufacturer\":\"nonummy\",\"availability\":\"InStock\"},{\"id\":\"r5fD2JsKobkwAOQ7yMLX\",\"category\":\"Beanies\",\"name\":\"ut erat\",\"colors\":[\"Orange\",\"Violet\",\"Indigo\"],\"price\":122,\"manufacturer\":\"condimentum\",\"availability\":\"OutOfStock\"}]";

        String jsonFacemasks = "[{\"id\":\"nHqm5T0en3sLPIJRNlJ6\",\"category\":\"Facemasks\",\"name\":\"justo maecenas rhoncus\",\"colors\":[\"Pink\",\"Blue\"],\"price\":530,\"manufacturer\":\"donec\",\"availability\":\"LessThan10\"},{\"id\":\"jyhExPtx33ydR5FiMWOY\",\"category\":\"Facemasks\",\"name\":\"est quam pharetra\",\"colors\":[\"Puce\"],\"price\":127,\"manufacturer\":\"praesent\",\"availability\":\"OutOfStock\"},{\"id\":\"IZYb5mL3eZVll4iPFEde\",\"category\":\"Facemasks\",\"name\":\"donec ut\",\"colors\":[\"Violet\"],\"price\":547,\"manufacturer\":\"praesent\",\"availability\":\"LessThan10\"},{\"id\":\"dUOP1eicICrMCMCyd6hl\",\"category\":\"Facemasks\",\"name\":\"aenean fermentum\",\"colors\":[\"Puce\",\"Yellow\"],\"price\":345,\"manufacturer\":\"luctus\",\"availability\":\"InStock\"},{\"id\":\"qfzVoDwwmluXicU1y6fI\",\"category\":\"Facemasks\",\"name\":\"id pretium iaculis\",\"colors\":[\"Violet\",\"Red\",\"Goldenrod\"],\"price\":591,\"manufacturer\":\"vulputate\",\"availability\":\"LessThan10\"},{\"id\":\"ndAm22feOzop6nwhrIo4\",\"category\":\"Facemasks\",\"name\":\"volutpat in congue\",\"colors\":[\"Mauv\",\"Orange\"],\"price\":457,\"manufacturer\":\"a\",\"availability\":\"LessThan10\"},{\"id\":\"G7jzohjttBIgvRyFdEJb\",\"category\":\"Facemasks\",\"name\":\"ut suscipit\",\"colors\":[\"Maroon\",\"Maroon\",\"Khaki\"],\"price\":282,\"manufacturer\":\"nulla\",\"availability\":\"LessThan10\"},{\"id\":\"bcH12ns5CRqCDpJMISiW\",\"category\":\"Facemasks\",\"name\":\"sed accumsan felis\",\"colors\":[\"Fuscia\"],\"price\":28,\"manufacturer\":\"nisi\",\"availability\":\"LessThan10\"},{\"id\":\"2TcocNl12AQ5pNi9tZFq\",\"category\":\"Facemasks\",\"name\":\"velit donec diam\",\"colors\":[\"Green\",\"Green\",\"Green\"],\"price\":178,\"manufacturer\":\"sapien\",\"availability\":\"InStock\"},{\"id\":\"ypZ6BX71YRRGcHpu8S0D\",\"category\":\"Facemasks\",\"name\":\"diam id\",\"colors\":[\"Turquoise\",\"Red\",\"Green\"],\"price\":348,\"manufacturer\":\"vestibulum\",\"availability\":\"LessThan10\"}]";

        String jsonGloves = "[{\"id\":\"T6Ak46JHX9gt5UndG9KR\",\"category\":\"Gloves\",\"name\":\"morbi quis tortor\",\"colors\":[\"Fuscia\",\"Orange\",\"Violet\"],\"price\":673,\"manufacturer\":\"eget\",\"availability\":\"OutOfStock\"},{\"id\":\"8GDB8vrt8RN1APWzIxmo\",\"category\":\"Gloves\",\"name\":\"vivamus tortor\",\"colors\":[\"Violet\"],\"price\":459,\"manufacturer\":\"feugiat\",\"availability\":\"OutOfStock\"},{\"id\":\"8D1a1gXfu8fBd1tkNnDS\",\"category\":\"Gloves\",\"name\":\"in hac\",\"colors\":[\"Crimson\",\"Pink\",\"Red\"],\"price\":491,\"manufacturer\":\"in\",\"availability\":\"LessThan10\"},{\"id\":\"PETAQXFOQdwvOLWrx66O\",\"category\":\"Gloves\",\"name\":\"urna pretium nisl\",\"colors\":[\"Pink\",\"Yellow\"],\"price\":657,\"manufacturer\":\"lectus\",\"availability\":\"LessThan10\"},{\"id\":\"KnpHSZI1qOnOQDQvaSn9\",\"category\":\"Gloves\",\"name\":\"porttitor lacus\",\"colors\":[\"Mauv\",\"Crimson\",\"Pink\"],\"price\":172,\"manufacturer\":\"praesent\",\"availability\":\"InStock\"},{\"id\":\"O6cT8Jcrh57oWygXC02C\",\"category\":\"Gloves\",\"name\":\"vivamus metus\",\"colors\":[\"Mauv\",\"Aquamarine\",\"Turquoise\"],\"price\":417,\"manufacturer\":\"orci\",\"availability\":\"LessThan10\"},{\"id\":\"hC7wz59gTw9ZPcCTgTpZ\",\"category\":\"Gloves\",\"name\":\"interdum eu\",\"colors\":[\"Purple\"],\"price\":319,\"manufacturer\":\"tellus\",\"availability\":\"LessThan10\"},{\"id\":\"u19XMwOo1mvn0ERoGPUd\",\"category\":\"Gloves\",\"name\":\"cubilia curae\",\"colors\":[\"Yellow\",\"Mauv\",\"Violet\"],\"price\":193,\"manufacturer\":\"pede\",\"availability\":\"InStock\"},{\"id\":\"aCacNB4yqHD3lgwmF7T3\",\"category\":\"Gloves\",\"name\":\"arcu libero rutrum\",\"colors\":[\"Crimson\",\"Aquamarine\"],\"price\":98,\"manufacturer\":\"varius\",\"availability\":\"OutOfStock\"},{\"id\":\"XtZSNh7TN9sIIvRPhG1Y\",\"category\":\"Gloves\",\"name\":\"donec semper sapien\",\"colors\":[\"Pink\"],\"price\":550,\"manufacturer\":\"mattis\",\"availability\":\"InStock\"}]";
        // </editor-fold>

        taskScheduler.schedule(productUpdateTask, Instant.now().plusMillis(1));

        // Wait until products have been processed
        while (productUpdateTask.isRunning().get()) {
            Thread.sleep(1000);
        }

        mvc.perform(get("/api/products/beanies").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(content().json(jsonBeanies));

        mvc.perform(get("/api/products/facemasks").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(content().json(jsonFacemasks));

        mvc.perform(get("/api/products/gloves").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(content().json(jsonGloves));
    }
}
