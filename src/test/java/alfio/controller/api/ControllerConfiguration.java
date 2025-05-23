/**
 * This file is part of alf.io.
 *
 * alf.io is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * alf.io is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with alf.io.  If not, see <http://www.gnu.org/licenses/>.
 */
package alfio.controller.api;

import alfio.controller.IndexController;
import alfio.controller.support.DataPreloaderManager;
import alfio.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;
import java.util.List;

@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackages = {"alfio.controller"})
@EnableWebMvc
public class ControllerConfiguration implements WebMvcConfigurer {


    @Autowired
    private ObjectMapper objectMapper;

    private MappingJackson2HttpMessageConverter jacksonMessageConverter(ObjectMapper objectMapper) {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // see https://github.com/springdoc/springdoc-openapi/issues/624#issuecomment-633155765
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        converters.add(converter);
        //
        converters.add(jacksonMessageConverter(objectMapper));
    }

    @Bean
    public IndexController indexController(EventRepository eventRepository,
                                           TicketReservationRepository ticketReservationRepository,
                                           SubscriptionRepository subscriptionRepository,
                                           DataPreloaderManager dataPreloaderManager) {
        return new IndexController(eventRepository, ticketReservationRepository, subscriptionRepository, dataPreloaderManager);
    }
}
