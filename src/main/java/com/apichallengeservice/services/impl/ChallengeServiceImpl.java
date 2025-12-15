package com.apichallengeservice.services.impl;

import com.apichallengeservice.dtos.challenge.RequestChallengeDTO;
import com.apichallengeservice.dtos.challenge.ResponseChallengeDTO;
import com.apichallengeservice.dtos.challenge.UpdateChallengeDTO;
import com.apichallengeservice.exception.ApiException;
import com.apichallengeservice.exception.ErrorCode;
import com.apichallengeservice.mapper.ChallengeMapper;
import com.apichallengeservice.model.Challenge;
import com.apichallengeservice.repository.ChallengeRepository;
import com.apichallengeservice.services.ChallengeService;
import com.apichallengeservice.services.UserServiceClient;
import com.apichallengeservice.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {

    private final ChallengeRepository challengeRepository;

    private final UserServiceClient userServiceClient;

    @Override
    public ResponseChallengeDTO createChallenge(RequestChallengeDTO requestChallengeDTO) {

        boolean userExists = userServiceClient
                .getUserById(requestChallengeDTO.getCreatorUserId())
                .isPresent();

        if(!userExists){
            throw new ApiException(ErrorCode.RESOURCE_NOT_FOUND,
                    String.format("User with id %d does not exist",requestChallengeDTO.getCreatorUserId()));
        }

        this.isValideRange(requestChallengeDTO.getStartDate(), requestChallengeDTO.getEndDate());

        Challenge newChallenge = ChallengeMapper.toEntity(requestChallengeDTO);
        challengeRepository.save(newChallenge);

        return ChallengeMapper.toDTO(newChallenge);
    }

    @Override
    public ResponseChallengeDTO findChallengeByID(Long id) {

        Challenge challenge = challengeRepository.findById(id)
                .orElseThrow(()->new ApiException(ErrorCode.RESOURCE_NOT_FOUND,
                        String.format("Challenge with id %d not found",id)));

       return ChallengeMapper.toDTO(challenge);
    }

    @Override
    public Page<ResponseChallengeDTO> findAllChallenges(int page, int size){
        int safePage = Math.max(page, 0);
        int safeSize = Math.min(size, 50);

        Pageable pageable = PageRequest.of(safePage, safeSize, Sort.by("createdAt").descending());
        Page<Challenge> challenges = challengeRepository.findAll(pageable);

        return challenges.map(ChallengeMapper::toDTO);
    }

    @Override
    public ResponseChallengeDTO updateChallenges(UpdateChallengeDTO updateChallengeDTO, Long idChallenge){

        Challenge challenge = challengeRepository.findById(idChallenge)
                .orElseThrow(()->new ApiException(ErrorCode.RESOURCE_NOT_FOUND,
                        String.format("Challenge with id %d not found",idChallenge)));

        LocalDate startDate = updateChallengeDTO.getStartDate() != null? updateChallengeDTO.getStartDate() : challenge.getStartDate();
        LocalDate endDate = updateChallengeDTO.getEndDate() != null? updateChallengeDTO.getEndDate() : challenge.getEndDate();

        this.isValideRange(startDate, endDate);

        if(updateChallengeDTO.getTitle() != null) challenge.setTitle(updateChallengeDTO.getTitle());
        if(updateChallengeDTO.getDifficulty() != null) challenge.setDifficulty(updateChallengeDTO.getDifficulty());
        if(updateChallengeDTO.getDescription() != null) challenge.setDescription(updateChallengeDTO.getDescription());
        if(updateChallengeDTO.getCategory() != null) challenge.setCategory(updateChallengeDTO.getCategory());
        if(updateChallengeDTO.getStartDate() != null) challenge.setStartDate(updateChallengeDTO.getStartDate());
        if(updateChallengeDTO.getEndDate() != null) challenge.setEndDate(updateChallengeDTO.getEndDate());

        challengeRepository.save(challenge);
        return ChallengeMapper.toDTO(challenge);
    }

    public void deleteChallenge(Long id){
        Challenge challenge = challengeRepository.findById(id)
                .orElseThrow(()->new ApiException(ErrorCode.RESOURCE_NOT_FOUND,
                        String.format("Challenge with id %d not found",id)));

        challengeRepository.delete(challenge);
    }
    private void isValideRange(LocalDate startDate, LocalDate endDate){
        if(!Utils.dateComparator( startDate, endDate)){
            throw new ApiException(ErrorCode.INVALIDE_INPUT_DATA,
                    String.format("The end date %s must be after the start date %s.", startDate, endDate));
        }
    }

}
