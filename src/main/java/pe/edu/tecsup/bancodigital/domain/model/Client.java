package pe.edu.tecsup.bancodigital.domain.model;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class Client {

    private final String id;            // id
    private final String name;          // nombre
    private final String email;         // email
    private final String document;      // documento
    private final LocalDateTime createdAt; // fecha_creacion

    public Client(String id, String name, String email, String document, LocalDateTime createdAt) {
        // Validación de email simple según restricción (email LIKE '%@%.%')
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Formato de email inválido: " + email);
        }
        this.id = id;
        this.name = name;
        this.email = email;
        this.document = document;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }
}