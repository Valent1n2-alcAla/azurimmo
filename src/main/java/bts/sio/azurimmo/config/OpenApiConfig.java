package bts.sio.azurimmo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AZURIMMO - Tableau de Bord de Gestion")
                        .version("1.0.0")
                        .description("Interface de gestion immobilière centralisée. " +
                                     "Ce tableau permet de piloter les bâtiments, appartements et contrats.")
                        .contact(new Contact()
                                .name("Support AzurImmo")
                                .email("admin@azurimmo.fr"))
                        .license(new License()
                                .name("Propriété Privée SIO")));
    }
}