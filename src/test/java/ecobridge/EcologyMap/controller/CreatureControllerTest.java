//package ecobridge.EcologyMap.controller;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class CreatureControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void getAllCreatureLocations() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/creatures"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].creature_latitude").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].creature_longitude").exists());
//    }
//
//
//
//}