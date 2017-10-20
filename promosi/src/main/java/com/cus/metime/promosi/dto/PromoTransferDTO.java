package com.cus.metime.promosi.dto;

import com.cus.metime.promosi.domain.embeddable.ValidityPeriod;

import java.io.Serializable;

/**
 * Created by C-US on 9/28/2017.
 */
public class PromoTransferDTO implements Serializable {

    private String Id;
    private String segmentId;
    private ValidityPeriod validityPeriod;

}
