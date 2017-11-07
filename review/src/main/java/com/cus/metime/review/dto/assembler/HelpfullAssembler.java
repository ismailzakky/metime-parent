package com.cus.metime.review.dto.assembler;

import com.cus.metime.review.domain.HelpFull;
import com.cus.metime.review.domain.HelpFullBuilder;
import com.cus.metime.review.dto.HelpfullDTO;
import com.cus.metime.review.dto.HelpfullDTOBuilder;
import com.cus.metime.review.dto.ReviewDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Handoyo
 */
public class HelpfullAssembler {
    public HelpfullDTO toDTO(HelpFull helpFull) {
        return new HelpfullDTOBuilder()
                .setId(helpFull.getId())
                .setHelpfullCategory(helpFull.getHelpfullCategory())
                .setReview(new ReviewDTO())
                .setCreationalDate(helpFull.getCreationalDate())
                .createHelpfullDTO();
    }
    
    public HelpFull toDomain(HelpfullDTO helpfullDTO) {
        return new HelpFullBuilder()
                .setId(helpfullDTO.getId())
                .setHelpfullCategory(helpfullDTO.getHelpfullCategory())
                .setReview(new ReviewAssembler(null).toDomain(helpfullDTO.getReview()))
                .setCreationalDate(helpfullDTO.getCreationalDate())
                .createHelpFull();
    }
    
    public List<HelpfullDTO> toDTOs(List<HelpFull> helpFulls) {
        List<HelpfullDTO> helpfullDTOs = new ArrayList();
        for (HelpFull helpFull : helpFulls) {
            helpfullDTOs.add(toDTO(helpFull));
        }
        return helpfullDTOs;
    }
    
    public List<HelpFull> toDomains(List<HelpfullDTO> helpfullDTOs) {
        List<HelpFull> helpFulls = new ArrayList();
        for (HelpfullDTO helpfullDTO : helpfullDTOs) {
            helpFulls.add(toDomain(helpfullDTO));
        }
        return helpFulls;
    }
}
