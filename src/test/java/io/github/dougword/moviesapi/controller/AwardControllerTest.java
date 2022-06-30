package io.github.dougword.moviesapi.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class AwardControllerTest {

	@Autowired
	MockMvc mvc;

	@Test
	@DisplayName("Must have expected results according to imported csv")
	void testGetWinners() throws Exception {

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON);
		mvc.perform(request)
			.andExpect(status().isOk())
			.andExpect(jsonPath("min").isArray())
			.andExpect(jsonPath("$.min[0].producer", is("Joel Silver")))
			.andExpect(jsonPath("$.min[0].interval", is(1)))
			.andExpect(jsonPath("$.min[0].previousWin", is(1990)))
			.andExpect(jsonPath("$.min[0].followingWin", is(1991)))
			.andExpect(jsonPath("max").isArray())
			.andExpect(jsonPath("$.max[0].producer", is("Matthew Vaughn")))
			.andExpect(jsonPath("$.max[0].interval", is(13)))
			.andExpect(jsonPath("$.max[0].previousWin", is(2002)))
			.andExpect(jsonPath("$.max[0].followingWin", is(2015)));
	}

}
