package com.example.projetangular;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter

public class UpdateStartDateDTO {
    private Date newStartDate;

    public Date getNewStartDate() {
        return newStartDate;
    }

    public void setNewStartDate(Date newStartDate) {
        this.newStartDate = newStartDate;
    }
}

