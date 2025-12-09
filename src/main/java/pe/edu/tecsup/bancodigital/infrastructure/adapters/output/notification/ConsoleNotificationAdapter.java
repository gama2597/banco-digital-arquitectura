package pe.edu.tecsup.bancodigital.infrastructure.adapters.output.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.bancodigital.application.ports.output.NotificationPort;

@Slf4j // Usamos Log4j2 a través de SLF4J
@Component
public class ConsoleNotificationAdapter implements NotificationPort {

    @Override
    public void notify(String message) {
        // Simulamos el envío de un correo/SMS imprimiendo en el log con formato
        log.info("📧 [NOTIFICACIÓN ENVIADA]: {}", message);
    }
}