package com.taxi.booking;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taxi.booking.booking.model.OrderInitView;
import com.taxi.booking.booking.model.OrderView;
import com.taxi.booking.cars.model.CarStatusInfo;
import com.taxi.booking.cars.model.TaxiInfo;
import com.taxi.booking.common.Geo;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Import(BaseIntegrationTestConfiguration.class)
@AutoConfigureMockMvc
public abstract class AbstractIntegrationTest {

  @LocalServerPort
  private int port;
  @Autowired
  protected MockMvc mockMvc;
  @Autowired
  protected ObjectMapper objectMapper;

  protected String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }

  private String map(Object object) throws JsonProcessingException {
    return objectMapper.writeValueAsString(object);
  }

  protected List<CarStatusInfo> loadAllCars() throws Exception {
    return objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders
            .get(createURLWithPort("/taxi"))
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString(), new TypeReference<>() {
    });
  }

  protected UUID registerNewCar(String driverId, String driverName, double lat, double lon)
      throws Exception {
    TaxiInfo info = new TaxiInfo();
    info.setDriverId(driverId);
    info.setDriverName(driverName);
    info.setLat(lat);
    info.setLon(lon);
    return objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders
            .post(createURLWithPort("/taxi/registration"))
            .content(map(info))
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString(), UUID.class);
  }

  protected double randomLatitude() {
    return Math.random() * 90;
  }

  protected double randomLongitude() {
    return Math.random() * 180;
  }

  protected List<OrderView> loadAllOrders() throws Exception {
    return objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders
            .get(createURLWithPort("/order"))
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString(), new TypeReference<>() {
    });
  }

  protected UUID registerNewOrder(Geo from, Geo to)
      throws Exception {
    OrderInitView info = new OrderInitView();
    info.setFrom(from);
    info.setTo(to);
    return objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders
            .post(createURLWithPort("/order"))
            .content(map(info))
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString(), UUID.class);
  }

  protected void takeOrderBy(UUID orderId, UUID carId) throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
            .patch(createURLWithPort("/order/" + orderId + "/TAKE"))
            .param("carId", carId.toString())
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  protected void startOrderBy(UUID orderId, UUID carId) throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
            .patch(createURLWithPort("/order/" + orderId + "/START"))
            .param("carId", carId.toString())
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  protected void completeOrderBy(UUID orderId, UUID carId) throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
            .patch(createURLWithPort("/order/" + orderId + "/COMPLETE"))
            .param("carId", carId.toString())
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
