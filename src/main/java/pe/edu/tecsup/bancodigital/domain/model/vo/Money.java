package pe.edu.tecsup.bancodigital.domain.model.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(BigDecimal amount) {
    public Money {
        if (amount == null) throw new IllegalArgumentException("Monto nulo no permitido");
        amount = amount.setScale(2, RoundingMode.HALF_EVEN);
    }
    public static Money of(double value) { return new Money(BigDecimal.valueOf(value)); }
    public boolean isNegative() { return this.amount.compareTo(BigDecimal.ZERO) < 0; }
    public boolean isGreaterThanOrEqual(Money other) { return this.amount.compareTo(other.amount) >= 0; }
    public Money add(Money other) { return new Money(this.amount.add(other.amount)); }
    public Money subtract(Money other) { return new Money(this.amount.subtract(other.amount)); }
}