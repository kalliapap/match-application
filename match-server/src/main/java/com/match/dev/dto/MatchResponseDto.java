package com.match.dev.dto;

import com.match.dev.entity.Match;

import java.io.Serial;
import java.io.Serializable;

public class MatchResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1971180919607416091L;

    private long matchId;

    public static MatchResponseDto from(Match match) {
        MatchResponseDto matchResponseDto = new MatchResponseDto();
        matchResponseDto.setMatchId(match.getId());
        return matchResponseDto;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    @Override
    public String  toString() {
        return "MatchResponseDto{" +
                "MatchId='" + matchId + '\'' +
                '}';
    }

}
