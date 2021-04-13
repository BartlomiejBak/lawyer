package pl.bartekbak.lawyer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PaymentDTO {

    private int paymentId;

    @Positive
    private double paymentValue;

    @Future
    private LocalDate paymentDate;

    private boolean paid = false;

    @Past
    private LocalDate paidDate;

    @Size(max = 255)
    private String comment;

    private boolean us;

    private boolean incoming;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentDTO that = (PaymentDTO) o;

        if (getPaymentId() != that.getPaymentId()) return false;
        if (Double.compare(that.getPaymentValue(), getPaymentValue()) != 0) return false;
        return getPaymentDate() != null ? getPaymentDate().equals(that.getPaymentDate()) : that.getPaymentDate() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getPaymentId();
        temp = Double.doubleToLongBits(getPaymentValue());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getPaymentDate() != null ? getPaymentDate().hashCode() : 0);
        return result;
    }
}
