package pe.edu.tecsup.bancodigital.infrastructure.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.tecsup.bancodigital.application.ports.output.AccountRepositoryPort;
import pe.edu.tecsup.bancodigital.application.ports.output.ClientRepositoryPort;
import pe.edu.tecsup.bancodigital.application.ports.output.NotificationPort;
import pe.edu.tecsup.bancodigital.application.ports.output.TransactionRepositoryPort;
import pe.edu.tecsup.bancodigital.application.usecases.BankAccountService;

@Configuration
@Slf4j
public class BeanConfiguration {

    @Bean
    public BankAccountService bankAccountService(
            ClientRepositoryPort clientRepo,
            AccountRepositoryPort accountRepo,
            TransactionRepositoryPort txRepo,
            NotificationPort notifPort) {

        // Uso del Singleton solo para loguear (demostración)
        log.info("Configurando servicio bancario. Moneda: {}", BankConfigSingleton.getInstance().getCurrency());

        return new BankAccountService(clientRepo, accountRepo, txRepo, notifPort);
    }
}