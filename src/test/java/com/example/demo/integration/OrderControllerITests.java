package com.example.demo.integration;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.data.OrderRepository;
import com.example.demo.data.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OrderControllerITests {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void setup() {
    orderRepository.deleteAll();
  }

  @Test
  public void givenOrderRequest_whenOrder_thenReturnSucess() throws Exception {
    //given - precondition or setup
    Order order = Order.builder()
        .firstName("Mustafa")
        .lastName("Güler")
        .email("mus@hotmail.com")
        .build();

    //when - action or behaviour that we are going test
    ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(order)));

    //then - verify the result or output using assert statements
    response.andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.firstName", is(order.getFirstName())))
        .andExpect(jsonPath("$.lastName", is(order.getLastName())))
        .andExpect(jsonPath("$.email", is(order.getEmail())));
  }


}






























