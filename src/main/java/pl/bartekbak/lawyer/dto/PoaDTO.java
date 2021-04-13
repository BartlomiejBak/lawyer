package pl.bartekbak.lawyer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PoaDTO {

    private int poaId;

    @Size(max = 50)
    private String type;

    @Size(max = 150)
    private String payment;

    private boolean kpc;

    private boolean termination;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate endDate;

    private boolean terminationNotificationDuty;

    private boolean terminationNotificationDutyCompleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PoaDTO poaDTO = (PoaDTO) o;

        if (getPoaId() != poaDTO.getPoaId()) return false;
        if (isKpc() != poaDTO.isKpc()) return false;
        if (getType() != null ? !getType().equals(poaDTO.getType()) : poaDTO.getType() != null) return false;
        if (getPayment() != null ? !getPayment().equals(poaDTO.getPayment()) : poaDTO.getPayment() != null)
            return false;
        return getStartDate() != null ? getStartDate().equals(poaDTO.getStartDate()) : poaDTO.getStartDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getPoaId();
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getPayment() != null ? getPayment().hashCode() : 0);
        result = 31 * result + (isKpc() ? 1 : 0);
        result = 31 * result + (getStartDate() != null ? getStartDate().hashCode() : 0);
        return result;
    }
}
