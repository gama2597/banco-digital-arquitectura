package pe.edu.tecsup.bancodigital.infrastructure.config;

/**
 * Patrón SINGLETON (Requisito Obligatorio)
 * Gestiona la configuración global del banco.
 */
public class BankConfigSingleton {

    private static BankConfigSingleton instance;
    private final String currency;
    private final double transferCommission;

    private BankConfigSingleton() {
        // Simulación de carga de configs
        this.currency = "PEN"; // Soles
        this.transferCommission = 5.00;
    }

    public static synchronized BankConfigSingleton getInstance() {
        if (instance == null) {
            instance = new BankConfigSingleton();
        }
        return instance;
    }

    public String getCurrency() { return currency; }
    public double getTransferCommission() { return transferCommission; }
}