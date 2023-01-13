package com.example.restdoc;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.restdocs.SpringCloudContractRestDocs;
import org.springframework.cloud.contract.wiremock.restdocs.WireMockRestDocs;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ComputeController.class)
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
class RestdocApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShouldReturnSquareRoot() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/compute/root/9.0"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("3.0")))
                .andDo(document("root",
                        SpringCloudContractRestDocs.dslContract()));
    }

    @Test
    public void testShouldSquareSquareRoot() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/compute/square/3.0"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("9.0")))
                .andDo(document("square",
                        SpringCloudContractRestDocs.dslContract()));

    }

}