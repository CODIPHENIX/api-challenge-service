package com.apichallengeservice.mapper;

import com.apichallengeservice.dto.ObjectiveDTO;
import com.apichallengeservice.dto.ObjectiveCreateDTO;
import com.apichallengeservice.dto.ObjectiveUpdateDTO;
import com.apichallengeservice.entity.ChallengeObjective;
import com.apichallengeservice.entity.ObjectiveType;

public class ObjectiveMapper {

    public static ObjectiveDTO toDTO(ChallengeObjective objective) {
        ObjectiveDTO dto = new ObjectiveDTO();

        dto.setId(objective.getId());
        dto.setObjectiveType(objective.getObjectiveType().name());
        dto.setTargetValue(objective.getTargetValue());
        dto.setUnit(objective.getUnit());

        return dto;
    }

    public static ChallengeObjective toEntity(ChallengeObjective objective, ObjectiveUpdateDTO dto) {
        objective.setObjectiveType(ObjectiveType.valueOf(dto.getObjectiveType()));
        objective.setTargetValue(dto.getTargetValue());
        objective.setUnit(dto.getUnit());
        return objective;
    }

    public static ChallengeObjective fromCreateDTO(ObjectiveCreateDTO dto) {
        ChallengeObjective obj = new ChallengeObjective();
        obj.setObjectiveType(ObjectiveType.valueOf(dto.getObjectiveType()));
        obj.setTargetValue(dto.getTargetValue());
        obj.setUnit(dto.getUnit());
        return obj;
    }
}
