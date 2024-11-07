package com.match.dev.dto;

import com.match.dev.enumeration.SportEnum;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MatchUpdateRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 2022897496435044550L;

    @NotNull
    private long id;
    private String description;
    private LocalDate matchDate;
    private LocalTime matchTime;
    private String teamA;
    private String teamB;
    private SportEnum sport;

    private List<MatchOddDto> matchOdds;

    public MatchUpdateRequestDto() {
    }

    private MatchUpdateRequestDto(Builder builder) {
        setId(builder.id);
        setDescription(builder.description);
        setMatchDate(builder.matchDate);
        setMatchTime(builder.matchTime);
        setTeamA(builder.teamA);
        setTeamB(builder.teamB);
        setSport(builder.sport);
        setMatchOdds(builder.matchOdds);
    }

    @NotNull
    public long getId() {
        return id;
    }

    public void setId(@NotNull long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public LocalTime getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(LocalTime matchTime) {
        this.matchTime = matchTime;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public SportEnum getSport() {
        return sport;
    }

    public void setSport(SportEnum sport) {
        this.sport = sport;
    }

    public List<MatchOddDto> getMatchOdds() {
        return matchOdds;
    }

    public void setMatchOdds(List<MatchOddDto> matchOdds) {
        this.matchOdds = matchOdds;
    }

    public static class Builder {

        private long id;
        private String description;
        private LocalDate matchDate;
        private LocalTime matchTime;
        private String teamA;
        private String teamB;
        private SportEnum sport;
        private List<MatchOddDto> matchOdds;

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withMatchDate(LocalDate matchDate) {
            this.matchDate = matchDate;
            return this;
        }

        public Builder withMatchTime(LocalTime matchTime) {
            this.matchTime = matchTime;
            return this;
        }

        public Builder withTeamA(String teamA) {
            this.teamA = teamA;
            return this;
        }

        public Builder withTeamB(String teamB) {
            this.teamB = teamB;
            return this;
        }

        public Builder withSport(SportEnum sport) {
            this.sport = sport;
            return this;
        }

        public Builder withMatchOdds(List<MatchOddDto> matchOdds) {
            this.matchOdds = matchOdds;
            return this;
        }

        public MatchUpdateRequestDto build() {
            return new MatchUpdateRequestDto(this);
        }
    }
}
