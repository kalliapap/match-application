package com.match.dev.util;

import com.match.dev.dto.MatchDto;
import com.match.dev.dto.MatchOddDto;
import com.match.dev.dto.MatchOddRequestDto;
import com.match.dev.entity.Match;
import com.match.dev.entity.MatchOdds;
import com.match.dev.exception.MatchOddValidationFailedException;

import java.util.List;
import java.util.stream.Collectors;

public class MatchUtils {

    private static final List<String> specifiers = List.of("X", "1", "2");

    public static Match toMatchEntity(MatchDto requestDto) {
        return new Match.Builder()
                .withId(requestDto.getId())
                .withDescription(requestDto.getDescription())
                .withMatchDate(requestDto.getMatchDate())
                .withMatchTime(requestDto.getMatchTime())
                .withTeamA(requestDto.getTeamA())
                .withTeamB(requestDto.getTeamB())
                .withSport(requestDto.getSport())
                .withMatchOdds(toMatchOddsEntityList(requestDto.getMatchOdds()))
                .build();
    }

    public static MatchDto toMatchDto(Match match) {
        return new MatchDto.Builder()
                .withId(match.getId())
                .withDescription(match.getDescription())
                .withMatchDate(match.getMatchDate())
                .withMatchTime(match.getMatchTime())
                .withTeamA(match.getTeamA())
                .withTeamB(match.getTeamB())
                .withSport(match.getSport())
                .withMatchOdds(toMatchOddsDtoList(match.getMatchOdds()))
                .build();

    }

    public static MatchOdds toMatchOddsEntity(MatchOddDto odd) {
        return new MatchOdds.Builder()
                .withId(odd.getId())
                .withSpecifier(odd.getSpecifier())
                .withOdd(odd.getOdd())
                .withMatch(odd.getMatch())
                .build();
    }

    public static MatchOdds toMatchOddsEntity(MatchOddRequestDto odd, Match match) {
        return new MatchOdds.Builder()
                .withId(odd.getId())
                .withSpecifier(odd.getSpecifier())
                .withOdd(odd.getOdd())
                .withMatch(match)
                .build();
    }

    public static MatchOddDto toMatchOddsDto(MatchOdds odd) {
        return new MatchOddDto.Builder()
                .withId(odd.getId())
                .withSpecifier(odd.getSpecifier())
                .withOdd(odd.getOdd())
                .withMatch(odd.getMatch())
                .build();
    }

    public static MatchOddRequestDto toMatchOddsRequestDto(MatchOdds odd) {
        return new MatchOddRequestDto.Builder()
                .withId(odd.getId())
                .withSpecifier(odd.getSpecifier())
                .withOdd(odd.getOdd())
                .withMatchId(odd.getMatch().getId())
                .build();
    }

    public static List<MatchOddDto> toMatchOddsDtoList(List<MatchOdds> matchOddsList) {
        return matchOddsList.stream()
                .map(MatchUtils::toMatchOddsDto)
                .collect(Collectors.toList());
    }

    public static List<MatchOdds> toMatchOddsEntityList(List<MatchOddDto> matchOddsList) {
        return matchOddsList.stream()
                .map(MatchUtils::toMatchOddsEntity)
                .collect(Collectors.toList());
    }

    public static void validateOdds(List<MatchOddDto> odds) throws MatchOddValidationFailedException {
        for (MatchOddDto odd : odds) {
            if ((odd.getOdd() <= 0 || !isSpecifierValid(odd.getSpecifier()))) {
                throw new MatchOddValidationFailedException("Invalid odd or specifier!");
            }
        }
    }

    public static boolean isSpecifierValid(String value) {
        return specifiers.contains(value);
    }
}
