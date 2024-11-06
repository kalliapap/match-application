package com.match.dev.dto;

import com.match.dev.enumeration.SportEnum;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MatchDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 2022897496435044550L;

    private long id;

    private String description;

    @NotNull
    @FutureOrPresent
    private LocalDate matchDate;

    @NotNull
    private LocalTime matchTime;

    @NotNull
    private String teamA;

    @NotNull
    private String teamB;

    @NotNull
    private SportEnum sport;

    @NotNull
    private List<MatchOddDto> matchOdds;

    public MatchDto() {
    }

    private MatchDto(Builder builder){
        setId(builder.id);
        setDescription(builder.description);
        setMatchDate(builder.matchDate);
        setMatchTime(builder.matchTime);
        setTeamA(builder.teamA);
        setTeamB(builder.teamB);
        setSport(builder.sport);
        setMatchOdds(builder.matchOdds);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotNull LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(@NotNull LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public @NotNull LocalTime getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(@NotNull LocalTime matchTime) {
        this.matchTime = matchTime;
    }

    public @NotNull String getTeamA() {
        return teamA;
    }

    public void setTeamA(@NotNull String teamA) {
        this.teamA = teamA;
    }

    public @NotNull String getTeamB() {
        return teamB;
    }

    public void setTeamB(@NotNull String teamB) {
        this.teamB = teamB;
    }

    public @NotNull SportEnum getSport() {
        return sport;
    }

    public void setSport(@NotNull SportEnum sport) {
        this.sport = sport;
    }

    public @NotNull List<MatchOddDto> getMatchOdds() {
        return matchOdds;
    }

    public void setMatchOdds(@NotNull List<MatchOddDto> matchOdds) {
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

        public MatchDto build() {
            return new MatchDto(this);
        }
    }
}
